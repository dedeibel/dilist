# omniplaylist

Provies a comfortable way to access all channels from digitally imported ([di.fm](http://di.fm/))

Get the current playlist [difm.pls](http://limitless-shore-5721.herokuapp.com/).

_(this is an unofficial app and not supported by the di.fm folks)_

[![Build Status](https://travis-ci.org/dedeibel/dilist.png)](https://travis-ci.org/dedeibel/dilist)

## Usage

```
$ lein run | head -n 11
[playlist]
NumberOfEntries = 480
File1 = http://pub7.di.fm:80/di_ambient_aacplus
Title1 = di.fm - Ambient
Length1 = -1
File2 = http://pub4.di.fm:80/di_ambient_aacplus
Title2 = di.fm - Ambient
Length2 = -1
File3 = http://pub1.di.fm:80/di_ambient_aacplus
Title3 = di.fm - Ambient
Length3 = -1
```

To a file

```
$ lein run > difm.pls
```

## License

Copyright © 2013 Benjamin Peter

Distributed under the Eclipse Public License, the same as Clojure.
