package com.github.srec.rec;

import com.github.srec.command.CallEventCommand;
import com.github.srec.rec.common.DefaultComponentDecoder;
import com.github.srec.rec.component.*;
import com.github.srec.ui.SRecForm;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The recorder used to record the user's interactions with the Swing application.
 *
 * @author Victor Tatai
 */
public class Recorder implements EventRecorder {
    private static final Logger logger = Logger.getLogger(Recorder.class);
    private List<ComponentRecorder> componentRecorders = new ArrayList<ComponentRecorder>();
    private SRecForm srecForm;

    /**
     * The list of ignored containers, such as the recording frame itself.
     */
    private List<Container> ignoredContainers = new ArrayList<Container>();
    private boolean recording;
    private CallEventCommand lastEvent;

    public Recorder(SRecForm form) {
        srecForm = form;
        componentRecorders.add(new ButtonClickRecorder(this));
        componentRecorders.add(new WindowActivationRecorder(this));
        componentRecorders.add(new TextFieldRecorder(this, new DefaultComponentVisibility()));
        componentRecorders.add(new SelectDropDownRecorder(this, new DefaultComponentDecoder(), new DefaultComponentVisibility()));
        componentRecorders.add(new CheckTextRecorder(this));
    }

    public void init() {
        for (ComponentRecorder recorder : componentRecorders) {
            recorder.register();
        }
    }

    public void shutdown() {
        for (ComponentRecorder recorder : componentRecorders) {
            recorder.unregister();
        }
    }

    public void record(CallEventCommand event) {
        if (!recording || isIgnored(event.getComponent()) || isOnJavaConsole(event.getComponent())) return;
        if (StringUtils.isBlank(event.getComponentLocator())) {
            logger.warn("Component has no way of being located (no name or label): " + event.getComponent());
            return;
        }
        event.record(this, lastEvent);
        lastEvent = event;
    }

    public void addEvent(CallEventCommand event) {
        srecForm.addTextNewLine(event.print());
    }

    protected boolean isIgnored(Component component) {
        for (Container container : ignoredContainers) {
            boolean matches = isParentOf(container, component);
            if (matches) return true;
        }
        return false;
    }

    private boolean isParentOf(Container container, Component component) {
        return container == component || component != null && component.getParent() != null && (component.getParent() == container || isParentOf(container, component.getParent()));
    }

    private boolean isOnJavaConsole(Component component) {
        if (component == null) return false;
        JFrame frame = getParentFrame(component);
        return frame != null && frame.getTitle().equalsIgnoreCase("Java Console");
    }

    private JFrame getParentFrame(Component component) {
        if (component == null) return null;
        while (component.getParent() != null && !(component instanceof JFrame)) {
            component = component.getParent();
        }
        if (component instanceof JFrame) {
            return (JFrame) component;
        }
        return null;
    }

    public void replaceLastEvent(CallEventCommand event) {
        logger.debug("Replacing the last event with: " + event);
        srecForm.replaceLastEvent(event);
    }

    public void addIgnoredContainer(Container container) {
        ignoredContainers.add(container);
    }

    public boolean isRecording() {
        return recording;
    }

    public void setRecording(boolean recording) {
        this.recording = recording;
    }

    public CallEventCommand getLastEvent() {
        return lastEvent;
    }
}
