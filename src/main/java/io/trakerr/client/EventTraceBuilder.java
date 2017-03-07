package io.trakerr.client;

import io.trakerr.model.InnerStackTrace;
import io.trakerr.model.StackTraceLine;
import io.trakerr.model.StackTraceLines;
import io.trakerr.model.Stacktrace;

import java.util.List;

class EventTraceBuilder {
	
	/**
	 * Takes the thrown error and parses the StackTrace.
	 * @param e Error (usually a derivitive class of Exception) to parse.
	 * @return StackTrace object which is a list of InnerStackTraces.
	 */
    static Stacktrace getEventTraces(Throwable e) {
        if (e == null) return null;
        Stacktrace traces = new Stacktrace();
        addStackTraces(traces, e);
        return traces;
    }

    /**
     * Takes a StackTrace object and adds a new trace to it with the parse exception.
     * @param traces The list of InnerStackTraces to add to.
     * @param exception The Exception to parse.
     */
    private static void addStackTraces(List<InnerStackTrace> traces, Throwable exception) {
        InnerStackTrace newTrace = new InnerStackTrace();

        newTrace.setTraceLines(getEventTraceLines(exception));
        newTrace.setType(exception.getClass().getName());
        newTrace.setMessage(exception.getMessage());
        traces.add(newTrace);

        if (exception.getCause() != null) {
            addStackTraces(traces, exception.getCause());
        }
    }

    /**
     * Get each line in the Exception, parse it, and add it to a StackTraceLines. 
     * @param exception Exception to parse.
     * @return Returns a list of StackTraceLine (StackTraceLine object).
     */
    private static StackTraceLines getEventTraceLines(Throwable exception) {
        StackTraceLines lines = new StackTraceLines();
        if (exception == null || exception.getStackTrace().length == 0) {
            StackTraceLine line = new StackTraceLine();
            lines.add(line);
            line.setFile("unknown");
            line.setLine(0);
            line.setFunction("unknown");
            return lines;
        }

        for (StackTraceElement element : exception.getStackTrace()) {
            StackTraceLine line = new StackTraceLine();
            line.setFile(element.getFileName());
            line.setLine(element.getLineNumber());
            line.setFunction(element.getClassName() + "." + element.getMethodName());

            lines.add(line);
        }

        return lines;
    }
}
