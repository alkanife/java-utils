// MIT License
//
// Copyright (c) 2025 Arthur Beau ("Alkanife", "Alka") @ https://alka.dev
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
package dev.alka.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonKeys {

    private String json;
    private LinkedHashMap<String, Object> keys;

    private final Gson gson;

    /**
     * Translate your JSON value to a String-Object map (key/value)
     *
     * @param json String JSON, existing or empty (`{}`)
     */
    public JsonKeys(String json) {
        this.json = json;
        this.keys = new LinkedHashMap<>();
        this.gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        Map<?, LinkedTreeMap<?, ?>> map = gson.fromJson(json, Map.class);

        if (map == null) {
            throw new NullPointerException("Empty json");
        }

        for (Map.Entry<?, LinkedTreeMap<?, ?>> entry : map.entrySet())
            readEntry(entry.getKey() + "", entry);
    }

    /**
     * Get JSON
     *
     * @return Current JSON string
     */
    public String getJson() {
        return json;
    }

    /**
     * Change the current JSON string. DOES NOT UPDATE THE key/value MAP, USE updateJson()!
     *
     * @param json JSON String
     */
    public void setJson(String json) {
        this.json = json;
    }

    /**
     * Get keys
     *
     * @return The key/value HashMap
     */
    public HashMap<String, Object> getKeys() {
        return keys;
    }

    /**
     * Directly set the key/value HashMap
     *
     * @param keys A new HashMap
     */
    public void setKeys(LinkedHashMap<String, Object> keys) {
        this.keys = keys;
    }

    /**
     * Create a key with an associated value
     *
     * @param key The key
     * @param object The value
     */
    public void createKey(String key, Object object) {
        keys.put(key, object);
        updateJson();
    }

    /**
     * Get a key's value
     *
     * @param key The key
     * @return The value
     */
    public Object getKey(String key) {
        return keys.get(key);
    }

    /**
     * Get a key's value, replace with the default value if not found
     *
     * @param key The key
     * @param defaultValue The default value
     * @return The final value
     */
    public Object getKey(String key, Object defaultValue) {
        if (!keys.containsKey(key)) {
            keys.put(key, defaultValue);
            updateJson();
        }

        return keys.get(key);
    }

    /**
     * Update a key's value. Creates the key if it does not exist
     *
     * @param key The key
     * @param object The value
     */
    public void updateKey(String key, Object object) {
        if (keys.get(key) != null) {
            keys.remove(key);
        }

        keys.put(key, object);

        updateJson();
    }

    /**
     * Delete some key and it's value
     *
     * @param key The key
     */
    public void deleteKey(String key) {
        if (keys.get(key) != null) {
            keys.remove(key);
        }

        updateJson();
    }

    private void readEntry(String address, LinkedTreeMap.Entry<?, ?> entry) {
        if (entry.getValue() instanceof LinkedTreeMap<?, ?> mapValue) {
            for (LinkedTreeMap.Entry<?, ?> entryValue : mapValue.entrySet())
                readEntry(address + "." + entryValue.getKey(), entryValue);
        } else {
            keys.put(address, entry.getValue());
        }
    }

    /**
     * Update the JSON string with the values of the key/value map
     */
    public void updateJson() {
        Map<String, Object> nestedMap = new HashMap<>();

        for (Map.Entry<String, Object> entry : keys.entrySet()) {
            String[] parts = entry.getKey().split("\\.");
            Map<String, Object> current = nestedMap;

            for (int i = 0; i < parts.length; i++) {
                String part = parts[i];
                if (i == parts.length - 1) {
                    current.put(part, entry.getValue());
                } else {
                    if (!current.containsKey(part) || !(current.get(part) instanceof Map)) {
                        current.put(part, new HashMap<String, Object>());
                    }
                    current = (Map<String, Object>) current.get(part);
                }
            }
        }

        this.json = gson.toJson(nestedMap);
    }

}
