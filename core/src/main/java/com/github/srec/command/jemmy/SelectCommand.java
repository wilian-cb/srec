package com.github.srec.command.jemmy;

import com.github.srec.command.ExecutionContext;
import com.github.srec.command.SRecCommand;
import com.github.srec.command.exception.IllegalParametersException;
import com.github.srec.command.value.NumberValue;
import com.github.srec.command.value.StringValue;
import com.github.srec.command.value.Type;
import com.github.srec.command.value.Value;
import com.github.srec.util.Utils;
import org.netbeans.jemmy.JemmyException;

import java.util.Map;

import static com.github.srec.jemmy.JemmyDSL.comboBox;

/**
 * @author Victor Tatai
 */
@SRecCommand
public class SelectCommand extends JemmyEventCommand {
    public SelectCommand() {
        super("select", param(LOCATOR, Type.STRING), param("item", Type.STRING, true, null),
                param("index", Type.NUMBER, true, null));
    }

    @Override
    protected void runJemmy(ExecutionContext ctx, Map<String, Value> params) throws JemmyException {
        if (params.containsKey("item")) comboBox(coerceToString(params.get(LOCATOR), ctx)).select(((StringValue) params.get("item")).get());
        else if (params.containsKey("index")) comboBox(coerceToString(params.get(LOCATOR), ctx)).select(((NumberValue) params.get("index")).get().intValue());
        else throw new IllegalParametersException("Illegal parameters " + Utils.asString(params) + " for select command");
    }
}