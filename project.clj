(defproject dilist "0.1.0-SNAPSHOT"
  :description "Downloads all the playlists from http://di.fm and provides one conactenated list"
  :url "http://benjaminpeter.name"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
                  [org.clojure/clojure "1.4.0"]
                  [clj-http "0.5.4"]
                  [enlive "1.0.1"]
                 ]
  :main dilist.core)
