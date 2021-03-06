package com.github.srec.command.jemmy;

import com.github.srec.command.ExecutionContext;
import com.github.srec.command.SRecCommand;
import com.github.srec.command.value.Value;
import org.netbeans.jemmy.JemmyException;

import java.util.Map;

import static com.github.srec.jemmy.JemmyDSL.dialog;

/**
 * @author Victor Tatai
 */
@SRecCommand
public class DialogActivateCommand extends JemmyEventCommand {
    public DialogActivateCommand() {
        super("dialog_activate", params(LOCATOR));
    }

    @Override
    protected void runJemmy(ExecutionContext ctx, Map<String, Value> params) throws JemmyException {
        dialog(coerceToString(params.get(LOCATOR), ctx));
    }
}