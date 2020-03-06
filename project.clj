(defproject yaml2edn "0.1.0"
  :description "A little program that converts .yaml files to .edn"
  :url "https://github.com/lsevero/yaml2edn"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [lsevero/yaml "1.0.0"]]
  :main yaml2edn.core
  :aot :all
  :uberjar-name "yaml2edn.jar"
  :repl-options {:init-ns yaml2edn.core
                 :port 17000})
