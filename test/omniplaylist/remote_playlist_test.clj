(ns omniplaylist.remote_playlist-test
  (:require name.benjaminpeter.clj-pls)
  (:use omniplaylist.remote-playlist
        midje.sweet
        omniplaylist.download))

(fact "A playlist with no tracks must be returned in case of an empty stream url."
      (-> (map->RemotePlaylist {:format "MP3" :name "128k Broadband" :url nil}) download-and-parse-playlist :tracks)
      => (every-pred coll? empty?))

(def playlist-test-structure
  {:entries 3,
   :version "2",
   :files
   [{:url "http://pub4.di.fm:80/di_chiptunes_aacplus",
     :title "Digitally Imported - Chiptunes",
     :length -1}
    {:url "http://pub1.di.fm:80/di_chiptunes_aacplus",
     :title "Digitally Imported - Chiptunes",
     :length -1}
    {:url "http://pub2.di.fm:80/di_chiptunes_aacplus",
     :length -1,
     :title "Digitally Imported - Chiptunes"}]})


(def test-stream (map->RemotePlaylist
                   {
                    :format "MP3"
                    :name "128k Broadband" 
                    :url "http://example.com/public2/chiptunes.pls"}))

(fact "A playlist with tracks must be returned"
      (:tracks (download-and-parse-playlist test-stream)) => (contains {
                                                                        :title "Digitally Imported - Chiptunes"
                                                                        :url "http://pub4.di.fm:80/di_chiptunes_aacplus"
                                                                        :format nil
                                                                        :length -1}
                                                                       {
                                                                        :title "Digitally Imported - Chiptunes"
                                                                        :url "http://pub1.di.fm:80/di_chiptunes_aacplus"
                                                                        :format nil
                                                                        :length -1})

      (provided
        (name.benjaminpeter.clj-pls/parse anything)    => playlist-test-structure
        (omniplaylist.download/as-stream anything) => nil
        )
      (count (:tracks (download-and-parse-playlist test-stream))) => 3
      (provided
        (name.benjaminpeter.clj-pls/parse anything) => playlist-test-structure
        (omniplaylist.download/as-stream anything) => nil))

