package net.andreinc.mockneat.abstraction;

/**
 * Copyright 2017, Andrei N. Ciobanu

 Permission is hereby granted, free of charge, to any user obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
 persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, FREE_TEXT OF OR PARAM CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS PARAM THE SOFTWARE.
 */


import net.andreinc.mockneat.utils.ValidationUtils;

import java.util.function.Supplier;
import java.util.stream.DoubleStream;

import static java.util.stream.IntStream.range;
import static net.andreinc.mockneat.utils.ValidationUtils.isTrue;

public interface MockUnitDouble extends MockUnit<Double> {

    /**
     * <p>Transforms an existing {@code MockUnitDouble} into a {@code MockUnit<DoubleStream>}.</p>
     *
     * <p>The DoubleStream will be "infinite" and will contain values generated by the internal {@code MockUnitDouble} supplier.</p>
     *
     * @return A new {@code MockUnit<DoubleStream>}
     */
    default MockUnit<DoubleStream> doubleStream() {
        Supplier<DoubleStream> supp = () -> DoubleStream.generate(supplier()::get);
        return () -> supp;
    }

    /**
     * <p>Transforms an existing {@code MockUnitDouble} into a {@code MockUnit<double[]>}.</p>
     *
     * <p>The values in the array will be generated using the {@code MockUnitDouble} supplier.</p>
     *
     * @param size The size of the array.
     * @return A new {@code MockUnit<double[]>}
     */
    default MockUnit<double[]> arrayPrimitive(int size) {
        isTrue(size>=0, ValidationUtils.SIZE_BIGGER_THAN_ZERO);
        Supplier<double[]> supp = () -> {
            final double[] result = new double[size];
            range(0, size).forEach(i -> result[i] = val());
            return result;
        };
        return () -> supp;
    }

    /**
     * <p>Transforms an existing {@code MockUnitDouble} into a {@code MockUnit<Double[]>}</p>
     *
     * <p>The values in the array will be generated using the {@code MockUnitDouble} supplier.</p>
     *
     * @param size The size of the array
     * @return A new {@code MockUnit<Double[]>}
     */
    default MockUnit<Double[]> array(int size) {
        isTrue(size>=0, ValidationUtils.SIZE_BIGGER_THAN_ZERO);
        Supplier<Double[]> supp = () -> {
            final Double[] result = new Double[size];
            range(0, size).forEach(i -> result[i] = val());
            return result;
        };
        return () -> supp;
    }
}
