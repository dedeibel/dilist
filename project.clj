(defproject omniplaylist "0.1.0-SNAPSHOT"
  :description "Downloads some interesting playlists and provides one conactenated list"
  :url "http://benjaminpeter.name"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.4.0"]
                 [clj-http "0.5.4"]
                 [enlive "1.0.1"]
                 [org.clojure/tools.trace "0.7.5"]
                 [clj-pls "0.2.0"]
                 [org.ini4j/ini4j "0.5.2"]           ; java
                 ]
  :repositories [["mavenrepository" "http://repo1.maven.org/maven2/"]]
  :profiles { :dev {
                    :dependencies [[midje "1.5-RC1"]]
                    :plugins [[lein-midje "3.0-RC1"]]}}
  :main omniplaylist.core)
