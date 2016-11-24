package io.trakerr.client;

import io.trakerr.model.InnerStackTrace;
import io.trakerr.model.StackTraceLine;
import io.trakerr.model.StackTraceLines;
import io.trakerr.model.Stacktrace;

import java.util.List;

class EventTraceBuilder {
    static Stacktrace getEventTraces(Throwable e) {
        if (e == null) return null;
        Stacktrace traces = new Stacktrace();
        addStackTraces(traces, e);
        return traces;
    }

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
