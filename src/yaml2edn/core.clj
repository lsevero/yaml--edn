(ns yaml2edn.core
  (:require [yaml.core :as yaml]
            [clojure.walk :as walk]
            [clojure.string :as str]
            [clojure.pprint :as pp]
            [clojure.java.io :as io])
  (:import [flatland.ordered.map OrderedMap])
  (:gen-class))

(defn ordered-map?
  [m]
  (if (= (type m) OrderedMap)
    m
    false))

(defn ordered-map->map
  [node]
  (if-let [m (ordered-map? node)]
    (into {} m)
    node))

(defn convert-to-edn
  [args]
  (let [edn-str (->> args
                     first
                     yaml/from-file
                     (walk/postwalk ordered-map->map)
                     pp/pprint
                     with-out-str)]
    (case (count args)
      1 (spit (str/replace (first args) #"\.yml|\.yaml" ".edn") edn-str)
      2 (spit (second args) edn-str)
      (println "This program does no accept more than 3 args"))))

(comment (convert-to-edn "/home/severo/Documentos/cnab-layouts/config/itau/cnab400/cobranca.yml"))
(comment (ordered-map? (type (yaml/from-file "/home/severo/Documentos/cnab-layouts/config/itau/cnab400/cobranca.yml"))))

(defn -main [& args]
  (cond
    (< (count args) 1) (println "You need to pass the path of the file you want to convert")
    (or (= (first args) "-h")
        (= (first args) "--help")) (println "java -jar yaml2edn.jar <path to yaml> [optional <path to edn>]")
    :else (convert-to-edn args)))
