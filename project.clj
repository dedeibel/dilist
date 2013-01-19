(defproject dilist "0.1.0-SNAPSHOT"
  :description "Downloads all the playlists from http://di.fm and provides one conactenated list"
  :url "http://benjaminpeter.name"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                  [org.clojure/clojure "1.4.0"]
                  [clj-http "0.5.4"]
                  [enlive "1.0.1"]
                  [org.clojure/tools.trace "0.7.5"]
                  [org.ini4j/ini4j "0.5.2"]           ; java
                 ]
  :repositories [["mavenrepository" "http://repo1.maven.org/maven2/"]]
  :main dilist.core)
