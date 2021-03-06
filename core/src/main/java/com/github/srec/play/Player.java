package com.github.srec.play;

import static com.github.srec.util.Utils.closeWindows;
import static com.github.srec.util.Utils.runSwingMain;
import static org.apache.commons.lang.StringUtils.isBlank;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.github.srec.command.ExecutionContext;
import com.github.srec.command.ExecutionContextFactory;
import com.github.srec.command.TestCase;
import com.github.srec.command.TestSuite;
import com.github.srec.command.base.Command;
import com.github.srec.command.base.Command.CommandFlow;
import com.github.srec.command.exception.CommandExecutionException;
import com.github.srec.command.parser.ParseException;
import com.github.srec.command.parser.Parser;
import com.github.srec.command.parser.ParserFactory;
import com.github.srec.jemmy.ComponentMap;
import com.github.srec.jemmy.JemmyDSL;
import com.github.srec.rec.DefaultScreenShot;
import com.github.srec.rec.ScreenShot;
import com.github.srec.util.PropertiesReader;

/**
 * Class which plays srec scripts / commands.
 *
 * @author Victor Tatai
 */
public class Player {
    private static final Logger log =  Logger.getLogger(Player.class);
    private final List<PlayerError> errors = new ArrayList<PlayerError>();
    private long commandInterval = 50;
    private Parser parser;

    /**
     * Class to run before each test case. May be null.
     */
    private String classToRun;
    /**
     * Arguments when running the class to run. May be null.
     */
    private String[] classToRunArgs;

    private final boolean singleInstanceMode;
    private boolean failFast;
    private boolean failed;

    public Player(boolean singleInstanceMode) {
        this.singleInstanceMode = singleInstanceMode;
    }

    public Player() {
        this(false);
    }

    public Player init(boolean failFast) {
        JemmyDSL.init();
        String intervalString = PropertiesReader.getProperties()
            .getProperty(PropertiesReader.PLAYER_COMMAND_INTERVAL);
        if (!isBlank(intervalString)) {
            commandInterval = Integer.parseInt(intervalString);
        }

        // Overrides properties file if using the command line param
        commandInterval = getIntProperty("com.github.srec.commandInterval", commandInterval);

        parser = ParserFactory.create();
        this.failFast = failFast;
        return this;
    }

    public Player init() {
        return init(true);
    }


    public static long getIntProperty(String key, long defaultValue){
        String s = System.getProperty(key);
        if (isBlank(s) || s.startsWith("${")){
            return defaultValue;
        } else {
            try {
                return Long.parseLong(s);
            } catch (NumberFormatException e) {
                log.warn("Invalid '" + key + "'. Reverting to default value " + defaultValue);
                return defaultValue;
            }
        }

    }

    /**
     * Plays a script.
     *
     * @param file The script file name
     * @param testCases The test case to run, use null to run all
     * @param className The class name of the application under test, null to not launch - notice that the application is restarted for each TC
     * @param args The arguments to pass to the class above
     * @param properties
     *
     * @return The created player
     * @throws IOException The exception
     */
    public Player play(File file, String[] testCases, String className,
                       String[] args, Map<String, Object> properties) throws IOException {
        classToRun = className;
        classToRunArgs = args;
        play(file, testCases, properties);
        return this;
    }

    public Player play(File file) throws IOException {
        return play(file, null, null);
    }

    public Player play(File file, String[] testCases, Map<String, Object> properties) throws IOException {
        if (file == null) {
			return null;
		}
        if (file.isDirectory()) {
            log.info("Playing files inside dir: " + file.getCanonicalPath());
            // Run all scripts inside a directory
            File[] files = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String name) {
                    return name.endsWith(".rb");
                }
            });
            for (File file1 : files) {
                play(file1);
            }
            File[] subdirs = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.isDirectory();
                }
            });
            for (File subdir : subdirs) {
                play(subdir);
            }
            return this;
        } else {
            log.info("Playing file: " + file.getCanonicalPath());
            return play(new FileInputStream(file), file, testCases, properties);
        }
    }

    /**
     * Plays a script.
     *
     * @param is The input stream from where to read the script
     * @param file The originating file, may be null
     * @param testCases The names of the test cases to play, if null or empty all test cases are played
     * @return The player
     * @throws IOException in case there is an error reading the file
     */
    public Player play(InputStream is, File file, String[] testCases, Map<String, Object> properties) throws IOException {
        ExecutionContext context = ExecutionContextFactory.getInstance().create(null, null, file, file.getParentFile().getCanonicalPath());
        TestSuite suite = parser.parse(context, is, file.getCanonicalPath());
        suite.setProperties(properties);
        if (parser.getErrors().size() > 0) {
			throw new ParseException(parser.getErrors());
		}
        log.debug("Launching test suite: " + suite.getName());
        Set<String> testCasesSet = testCases == null || testCases.length == 0 ? null : new HashSet<String>(Arrays.asList(testCases));
        boolean appStarted = false;
        for (TestCase testCase : suite.getTestCases()) {
            if (testCasesSet != null && !testCasesSet.contains(testCase.getName())) {
				continue;
			}
            if ((!appStarted || !singleInstanceMode) && classToRun != null) {
                runSwingMain(classToRun, classToRunArgs);
                appStarted = true;
            }
            log.debug("Launching test case: " + testCase.getName());
            ExecutionContext testCaseEC = testCase.getExecutionContext();
            testCaseEC.setPlayer(this);
            testCaseEC.setTestCase(testCase);
            play(testCaseEC);
            if ((appStarted && !singleInstanceMode) && classToRun != null) {
                closeWindows();
                appStarted = false;
            }
        }
        return this;
    }

    public Command.CommandFlow play(ExecutionContext context) {
        if (failed && failFast) {
            return CommandFlow.EXIT;
        }
    	ComponentMap formerComponentMap = JemmyDSL.getComponentMap();
        JemmyDSL.setComponentMap(new ComponentMapSymbolsAdapter(context.getSymbols()));
        try {
            for (Command command : context.getCommands()) {
                log.debug("Running line: " + getLine(command) + ", command: " + command);
                try {
                    Command.CommandFlow flow = command.run(context);
                    if (flow == Command.CommandFlow.NEXT) {}
                    else if (flow == Command.CommandFlow.EXIT) {
						return Command.CommandFlow.EXIT;
					} else {
						throw new PlayerException("Flow management instruction " + flow + " from command "
						                               + command.getName() + " not supported");
					}
                } catch (CommandExecutionException e) {
                    handleError(context.getTestSuite(), context.getTestCase(), command, e);
                    break;
                } catch (Exception e) {
                	CommandExecutionException e1 = new CommandExecutionException(e);
                    handleError(context.getTestSuite(), context.getTestCase(), command, e1);
                    break;
                }
                try {
                    Thread.sleep(commandInterval);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return Command.CommandFlow.NEXT;
        } finally {
            JemmyDSL.setComponentMap(formerComponentMap);
        }
    }

    private String getLine(Command command) {
        if (command.getLocation() == null) {
			return "<NO LINE>";
		}
        return "" + command.getLocation().getLineNumber();
    }

    private void handleError(TestSuite testSuite, TestCase testCase, Command command, CommandExecutionException e) {
        PlayerError error = new PlayerError(testSuite == null ? "" : testSuite.getName(),
                testCase == null ? "" : testCase.getName(), command.getLocation(), e);
        System.err.println("Error on line " + command.getLocation().getLineNumber() + ":");
        System.err.println(command.getLocation().getLine());
        errors.add(error);
        log.debug("Error in script play", e);
        failed = true;
        ScreenShot shot = new DefaultScreenShot();
        Robot robot;
		try {
			robot = new Robot();
	        shot.captureDesktop(testSuite.getName() + "/" + testCase.getName(), robot);
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
    }



    public List<PlayerError> getErrors() {
        return errors;
    }

    public long getCommandInterval() {
        return commandInterval;
    }

    public void setCommandInterval(long commandInterval) {
        this.commandInterval = commandInterval;
    }

    /**
     * Prints all errors in stderr.
     */
    public void printErrors() {
        for (PlayerError playerError : getErrors()) {
            System.err.println(playerError);
        }
    }

    public static void main(final String[] args) throws IOException {
        runSwingMain(args[0], null);

        Player player = new Player(false).init();
        player.play(new File(args[1]));
        player.printErrors();
        closeWindows();
    }

}
