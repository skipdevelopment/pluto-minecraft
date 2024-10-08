package com.github.steveice10.opennbt.tag.builtin;

import com.github.steveice10.opennbt.tag.limiter.TagLimiter;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

/**
 * A tag containing an integer array.
 */
public class IntArrayTag extends Tag {
    public static final int ID = 11;
    private static final int[] EMPTY_ARRAY = new int[0];
    private int[] value;

    /**
     * Creates a tag.
     */
    public IntArrayTag() {
        this(EMPTY_ARRAY);
    }

    /**
     * Creates a tag.
     *
     * @param value The value of the tag.
     */
    public IntArrayTag(int[] value) {
        if (value == null) {
            throw new NullPointerException("value cannot be null");
        }
        this.value = value;
    }

    @Override
    public int[] getValue() {
        return this.value;
    }

    /**
     * Sets the value of this tag.
     *
     * @param value New value of this tag.
     */
    public void setValue(int[] value) {
        if (value == null) {
            throw new NullPointerException("value cannot be null");
        }
        this.value = value;
    }

    /**
     * Gets a value in this tag's array.
     *
     * @param index Index of the value.
     * @return The value at the given index.
     */
    public int getValue(int index) {
        return this.value[index];
    }

    /**
     * Sets a value in this tag's array.
     *
     * @param index Index of the value.
     * @param value Value to set.
     */
    public void setValue(int index, int value) {
        this.value[index] = value;
    }

    /**
     * Gets the length of this tag's array.
     *
     * @return This tag's array length.
     */
    public int length() {
        return this.value.length;
    }

    @Override
    public void read(DataInput in, TagLimiter tagLimiter, int nestingLevel) throws IOException {
        tagLimiter.countInt();
        this.value = new int[in.readInt()];
        tagLimiter.countBytes(4 * this.value.length);
        for(int index = 0; index < this.value.length; index++) {
            this.value[index] = in.readInt();
        }
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(this.value.length);
        for (int i : this.value) {
            out.writeInt(i);
        }
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntArrayTag that = (IntArrayTag) o;
        return Arrays.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.value);
    }

    @Override
    public final IntArrayTag clone() {
        return new IntArrayTag(this.value.clone());
    }

    @Override
    public int getTagId() {
        return ID;
    }
}
