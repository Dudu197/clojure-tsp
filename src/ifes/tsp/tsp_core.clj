(ns ifes.tsp.tsp-core
  (:gen-class))

(def num-cidades 5)
(def distancia-maxima 50)


(defn nome-mapa
  [cidade1 cidade2]
  (if (< cidade1 cidade2)
    (str cidade1 "->" cidade2)
    (str cidade2 "->" cidade1)
  )
)

(defn gerar-distancias-cidade
  [cidade]
  (for [_ (range cidade num-cidades)]
    (if (> 50 (rand-int 100))
      (inc (rand-int distancia-maxima))
      -1
    )
  )
)


(defn adicionar-distancia [mapa cidade1 cidade2 distancia] (assoc mapa (nome-mapa cidade1 cidade2) distancia))

(defn gerar-cidade
  [mapa cidade]
  (reduce (fn [_mapa dict] (adicionar-distancia _mapa cidade (first dict) (last dict))) mapa (zipmap (range (inc cidade) (inc num-cidades)) (gerar-distancias-cidade cidade)))
)

(defn distancia [mapa cidade1 cidade2] (get mapa (nome-mapa cidade1 cidade2)))

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

(defn rotas-validas
  [rotas]
  (filter (fn [rota] (> (get rota :custo) -1)) rotas)
)


(defn caminho-valido?
  [mapa cidade1 cidade2]
  (> (distancia mapa cidade1 cidade2) -1)
)

(defn cidade-valida?
  [mapa cidade]
  (>= (count (filter true? (vec (for [i (range 1 (inc num-cidades))] (and (not= i cidade) (caminho-valido? mapa cidade i)))))) 2))
(defn mapa-invalido
  [mapa]
  (some
    false?
    (vec (for [i (range 1 (inc num-cidades))] (cidade-valida? mapa i)))
    )
)



(defn gerar-cidades
  ([] (gerar-cidades 1 {}))
  ([n mapa]
   (if (== n num-cidades)
     mapa
     (recur (inc n) (gerar-cidade mapa n)))
   )
  )


(defn gerar-cidades-seguro
  ([] (gerar-cidades-seguro 1 {}))
  ([n mapa]
     (let  [[mapa-gerado] [(gerar-cidades n mapa)]]
       (if (mapa-invalido mapa-gerado) (recur n mapa) mapa-gerado)
     )
  )
)
