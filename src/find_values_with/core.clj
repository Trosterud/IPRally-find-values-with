(ns find-values-with.core
  (:require [cheshire.core :as json]
            [clojure.string :as str])
  (:gen-class))

(defn -main
  "Returns a list of the values containing the given substring from all key's 'value' from the given JSON file. 
   As input it takes the file path to the JSON file and the string to match values with."

  [json-file-path key substring]
  (let [get-parsed-json (json/parse-string (slurp json-file-path) true)
        substring-match? (fn [s] (str/includes? s substring))] ; or w/regex matching (boolean (re-find #"motor" s))

    ;; Perform a depth first walk/search by:
    ;; 1. Creating a lazy sequence of the vertices of the given JSON tree 
    ;; 2. Filter maps (substantive) by 3 criteria: map?, has a valid key, key value matches arg substring
    ;; 3. And lastly we map (verb) and return these filtered string values
    (->> (tree-seq #(or (map? %) (vector? %)) identity (first get-parsed-json))
         (filter #(and (map? %) ((keyword key) %) (substring-match? (get % (keyword key)))))
         (map (keyword key)))))
