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
package dev.alka.utils.jcommander;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterDescription;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class JCommandArranger {

    private final JCommander jCommander;

    public JCommandArranger(JCommander jCommander) {
        this.jCommander = jCommander;
    }

    /**
     * This method will arrange the jCommander parameters according to their .order().
     *
     * @return An LinkedHashMap of jCommander's parameters, with the parameter names as the key, and the description as the value.
     */
    public LinkedHashMap<String, String> getOrderedParameters() {
        HashMap<Integer, ParameterDescription> orderMap = new HashMap<>();

        for (ParameterDescription p : jCommander.getParameters())
            orderMap.put(p.getParameter().order(), p);

        LinkedHashMap<String, String> commands = new LinkedHashMap<>();

        for (int i = 1; i <= orderMap.size(); i++) {
            ParameterDescription param = orderMap.get(i);

            if (param != null)
                commands.put(param.getNames(), param.getDescription());
        }

        return commands;
    }
}
