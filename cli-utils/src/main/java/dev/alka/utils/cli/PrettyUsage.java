// MIT License
//
// Copyright (c) 2024 Arthur Beau ("Alkanife", "Alka") @ https://alka.dev
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
package dev.alka.utils.cli;

import java.util.*;

public class PrettyUsage {

    private final LinkedHashMap<String, String> values;

    private int paddingLeft = 0;
    private int paddingRight = 3;

    public PrettyUsage() {
        this.values = new LinkedHashMap<>();
    }

    public void importValue(String key, String value) {
        this.values.put(key, value);
    }

    public void importValues(Map<String, String> values) {
        this.values.putAll(values);
    }

    public int getPaddingLeft() {
        return paddingLeft;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    public int getPaddingRight() {
        return paddingRight;
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    /**
     * Create usage lines.
     *
     * @return A list of lines, an empty list if no values have been imported
     */
    public List<String> getLines() {
        int commandWidth = calcNameWidth();

        List<String> lines = new ArrayList<>();

        for (String value : values.keySet())
            lines.add(getPadding(paddingLeft) + formatCommand(commandWidth, value) + getPadding(paddingRight) + values.get(value));

        return lines;
    }

    private int calcNameWidth() {
        int width = 0;

        for (String command : values.keySet()) {
            if (command.length() > width) {
                width = command.length();
            }
        }

        return width;
    }

    private String formatCommand(int width, String name) {
        StringBuilder nameBuilder = new StringBuilder(name);

        while (nameBuilder.length() < width)
            nameBuilder.append(" ");

        return nameBuilder.toString();
    }

    private String getPadding(int padding) {
        return " ".repeat(Math.max(0, padding));
    }
}
