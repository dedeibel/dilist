(defproject omniplaylist "0.1.0-SNAPSHOT"
  :description "Downloads some interesting playlists and provides one conactenated list"
  :url "http://benjaminpeter.name"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                 [org.clojure/clojure "1.5.1"]
                 [clj-http "0.7.3"]
                 [enlive "1.1.1"]
                 [org.clojure/tools.trace "0.7.5"]
                 [clj-pls "0.2.2"]
                 [org.ini4j/ini4j "0.5.2"]           ; java
                 ]
  :repositories [["mavenrepository" "http://repo1.maven.org/maven2/"]]
  ; deprecated but added for travis ci with old leiningen version
  :dev-dependencies [[midje "1.5.1" :exclusions [org.clojure/clojure]]
                     [lein-midje "3.0.1"]]
  :profiles { :dev {
                    :dependencies [[midje "1.5.1"]]
                    :plugins [[lein-midje "3.0.1"]]}}
  :main omniplaylist.core)
