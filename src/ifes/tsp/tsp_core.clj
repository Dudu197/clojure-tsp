(ns ifes.tsp.tsp-core
  (:gen-class))

(def num-cidades 5)
(def distancia-maxima 50)


(defn gerar-distancias-cidade
  [cidade]
  (for [_ (range cidade num-cidades)]
    (if (rand-nth [true false])
      (rand-int distancia-maxima)
      -1)
    )
  )

(defn nome-mapa [cidade1 cidade2] (str cidade1 "->" cidade2))

(defn adicionar-distancia [mapa cidade1 cidade2 distancia] (assoc mapa (nome-mapa cidade1 cidade2) distancia))

(defn gerar-cidade
  [mapa cidade]
  (reduce (fn [_mapa dict] (adicionar-distancia _mapa cidade (first dict) (last dict))) mapa (zipmap (range (inc cidade) (inc num-cidades)) (gerar-distancias-cidade cidade)))
  )


(defn gerar-cidades
  ([] (gerar-cidades 1 {}))
  ([n mapa]
   (if (== n num-cidades)
     mapa
     (recur (inc n) (gerar-cidade mapa n)))
   )
  )

(defn distancia
  [mapa cidade1 cidade2]
  (if (< cidade1 cidade2)
    (get mapa (nome-mapa cidade1 cidade2))
    (get mapa (nome-mapa cidade2 cidade1))
    )
  )

(defn calcula-rota
  ([mapa rota] (calcula-rota mapa rota 0))
  ([mapa rota acc]
    (if (> (count rota) 1)
        (if (> (distancia mapa (first rota) (second rota)) 0)
          (calcula-rota mapa (rest rota) (+ acc (distancia mapa (first rota) (second rota))))
          (calcula-rota mapa [] -1)
        )
      acc
    )
  )
)