(ns find-values-with.core
  (:require [cheshire.core :as json]
            [clojure.string :as str])
  (:gen-class))

(defn -main
  "Returns a list of the values containing the given substring from all key's 'value' from the given JSON file. 
   As input it takes the file path to the JSON file and the string to match values with."

  [json-file-path key substring]
  (let [get-parsed-json (json/parse-string (slurp json-file-path) true)
        substring-match? (fn [s] (str/includes? s substring))]

    ;; Perform depth first search, filter for relevant objects, map :value
    (->> (tree-seq #(or (map? %) (vector? %)) identity (first get-parsed-json))
         (filter #(and (map? %) ((keyword key) %) (substring-match? (get % (keyword key)))))
         (map (keyword key)))))
