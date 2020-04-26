(ns find-values-with.core
  (:require [cheshire.core :as json]
            [clojure.string :as str]
            [clojure.java.io :as io])
  (:gen-class))

(defn -main
  "Returns a vector of the values containing the arg substring,
   from all key's matching the arg key, in the given JSON file."

  [json-file-path key substring]
  {:pre [(.exists (io/file json-file-path)) (string? key) (string? substring)]
   :post [(= (type %) clojure.lang.PersistentVector)]}

  (let [get-parsed-json (json/parse-string (slurp json-file-path) true)
        substring-match? (fn [s] (str/includes? s substring))] ; or w/regex matching (boolean (re-find #"motor" s))

    ;; Perform a depth first walk/search by:
    ;; 1. Creating a lazy sequence of the vertices of the given JSON tree 
    ;; 2. Filter maps (substantive) by 3 criteria: map?, has a valid key, key value matches arg substring
    ;; 3. And lastly we map (verb) and return these filtered string values
    (->> (tree-seq #(or (map? %) (vector? %)) identity (first get-parsed-json))
         (filter #(and (map? %) ((keyword key) %) (substring-match? (get % (keyword key)))))
         (mapv (keyword key))))) ; mapv turns the LazySeq into a Vector, without using Into
