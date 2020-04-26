(ns find-values-with.core
  (:require [cheshire.core :as json] [clojure.java.io])
  (:gen-class))

(defn -main
  "Returns parsed JSON from file, takes filepath string as input"
  [json-file-path]
  (let [get-parsed-json (json/parse-string (slurp json-file-path) true)]
    get-parsed-json))
