package com.slang;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by syanochara on 28/07/2017.
 */

public class SlangContext {
    public Map<String, Object> variables = new HashMap<String, Object>();
    public Map<String, Object> params = new HashMap<String, Object>();
    public SlangContext parentContext = null;

    public SlangContext() {
    }

    public SlangContext(SlangContext parentContext) {
    this.parentContext = parentContext;
    }

    public Object getVariable(String varname) throws SlangException {
        SlangContext c = this;
        while(c != null) {
            if(c.variables.containsKey(varname))return c.variables.get(varname);
            else c = c.parentContext;
        }
        throw new SlangException("Could not find variable \""+varname+"\"");
    }

    public Object getParam(String param) throws SlangException {
        SlangContext c = this;
        while(c != null) {
            if(c.params.containsKey(param))return c.params.get(param);
            else c = c.parentContext;
        }
        throw new SlangException("Could not find param \""+param+"\"");
    }
}
