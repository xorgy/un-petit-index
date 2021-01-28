# un petit index

A simple, inefficient multi-language text search index.

## Usage

```clojure
(require [je.suis.un-petit-index :as pi])

(def gramdb
  (pi/map-to-gramdb
   {:foo "I am a foo, nothing more, nothing less."
    :bar "At a bar, one has nothing more to do but pity a foo."}))
(def index (pi/gramdb-to-index gramdb))

(pi/normalized-gramdb-query gramdb "foo")
;; => {:foo 1, :bar 1}

(pi/normalized-index-query index "bar")
;; => {:bar 1, :foo 1/3}
```

## License

Copyright © 2021 Aaron Muir Hamilton <aaron@correspondwith.me>

Permission to use, copy, modify, and distribute this software for any
purpose with or without fee is hereby granted, provided that the above
copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
