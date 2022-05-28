package com.tdc.outbox.commons;

public interface ObjectParser<InputType, OutputType> {
    OutputType parse(final InputType input);
}