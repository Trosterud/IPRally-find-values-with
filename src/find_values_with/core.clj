(ns find-values-with.core
  (:require [cheshire.core :as json])
  (:gen-class))

(defn -main
  "Returns parsed JSON from file, takes filepath string as input"
  [json-file-path]
  (let [get-parsed-json (json/parse-string (slurp json-file-path) true)]
    (map :value
         (filter map?
                 (tree-seq #(or (map? %) (vector? %)) identity (first get-parsed-json))))))
