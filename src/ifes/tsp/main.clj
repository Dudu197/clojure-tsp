(ns ifes.tsp.main
  (:gen-class))

(def num-cidades 5)
(def distancia-maxima 50)


(defn gerar-distancias-cidade
  [cidade]
  (for [i (range cidade num-cidades)]
    (if (rand-nth [true false])
      (rand-int distancia-maxima)
      0)
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
    (get mapa (nome-mapa cidade1 cidade2) 0)
    (get mapa (nome-mapa cidade2 cidade1) 0)
    )
  )


(def mapa (gerar-cidades))

(println mapa)
(println (distancia mapa 1 2))
(println (distancia mapa 2 3))
(println (distancia mapa 5 4))

(defn -main
  [& args]
  (gerar-cidades)
)
