(defproject instaparse-c "0.1.0-SNAPSHOT"
  :description "Library for parsing C programs with instaparse"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [instaparse "1.4.3"]]
  :profiles {:dev {:resource-paths ["dev-resources/corpus"]
                   :dependencies [[me.raynes/fs "1.4.6"]
                                  [proto-repl "0.3.1"]]}})
