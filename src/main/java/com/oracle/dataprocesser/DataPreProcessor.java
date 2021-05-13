package com.oracle.dataprocesser;

import com.oracle.functions.Encoder;
import com.oracle.functions.Validator;

import java.util.List;
import java.util.stream.Collectors;

public class DataPreProcessor<T> {

    public Processor<T> with(List<T> data) {
        return new Processor<>(data);
    }

    public static class Processor<T> {
        List<T> data;

        public Processor(List<T> data) {
            this.data = data;
        }

        public Processor<T> validate(Validator<T> validator) {
            this.data = data.stream().filter(validator::valid).collect(Collectors.toList());
            return this;
        }

        public Processor<T> encode(Encoder<T> encoder) {
            this.data = data.stream().map(encoder::encode).collect(Collectors.toList());
            return this;
        }

        public List<T> get() {
            return this.data;
        }
    }

}
