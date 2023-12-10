(ns ifes.tsp.tsp-core
  (:gen-class))

(def num-cidades "Número de cidades" 7)
(def distancia-maxima "Distância máxima entre as cidades" 50)
(def num-conexoes "Número me mínimo de conexões que uma cidade deve ter, se usando o mapa seguro" 4)

(defn definir-num-cidades "Define o número de cidades" [num] (def num-cidades num))
(defn definir-distancia-maxima "Define a distância máxima entre as cidades" [num] (def distancia-maxima num))
(defn definir-num-conexoes "Define a número de conexões entre as cidades" [num] (def num-conexoes num))


(defn nome-mapa
  "Gera a key a ser utilizada no dicionário do mapa"
  [cidade1 cidade2]
  (if (< cidade1 cidade2)
    (str cidade1 "->" cidade2)
    (str cidade2 "->" cidade1)
  )
)

(defn gerar-distancias-cidade
  "Calcula aleatoriamente a distância entre uma cidade e as demais. O valor -1 indica que uma cidade não está ligada a outra"
  [cidade]
  (for [_ (range cidade num-cidades)]
    (inc (rand-int distancia-maxima))
  )
)


(defn adicionar-distancia "Associa a distância entre duas cidades no mapa" [mapa cidade1 cidade2 distancia] (assoc mapa (nome-mapa cidade1 cidade2) distancia))

(defn gerar-cidade
  "Gera distâncias para uma cidade e adiciona as distâncias no mapa"
  [mapa cidade]
  (reduce (fn [_mapa dict] (adicionar-distancia _mapa cidade (first dict) (last dict))) mapa (zipmap (range (inc cidade) (inc num-cidades)) (gerar-distancias-cidade cidade)))
)

(defn distancia "Calcula a distância entre uma cidade e outra" [mapa cidade1 cidade2] (get mapa (nome-mapa cidade1 cidade2)))

(defn calcula-rota
  "Calcula o custo de uma rota"
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
  "Filtra apenas as rotas válidas (valor acima de -1)"
  [rotas]
  (filter (fn [rota] (> (get rota :custo) -1)) rotas)
)


(defn caminho-valido?
  "Valida se o caminho entre duas cidades é válido (valor acima de -1)"
  [mapa cidade1 cidade2]
  (> (distancia mapa cidade1 cidade2) -1)
)

(defn cidade-valida?
  "Valida se uma cidade é válida. Para ser válida ela deve se ligar com pelo menos outras duas cidades. Isso evita o algoritmo não conseguir sair da cidade"
  [mapa cidade]
  (>= (count (filter true? (vec (for [i (range 1 (inc num-cidades))] (and (not= i cidade) (caminho-valido? mapa cidade i)))))) num-conexoes))
(defn mapa-invalido
  [mapa]
  (some
    false?
    (vec (for [i (range 1 (inc num-cidades))] (cidade-valida? mapa i)))
    )
)



(defn gerar-cidades
  "Gerar as cidades aleatoriamente"
  ([] (gerar-cidades 1 {}))
  ([n mapa]
   (if (== n num-cidades)
     mapa
     (recur (inc n) (gerar-cidade mapa n)))
   )
  )


(defn gerar-cidades-seguro
  "Gera as cidades aleatoriamente até que todas as cidades se liguem com pelo menos duas outras cidades"
  ([] (gerar-cidades-seguro 1 {}))
  ([n mapa]
     (let  [[mapa-gerado] [(gerar-cidades n mapa)]]
       (if (mapa-invalido mapa-gerado) (recur n mapa) mapa-gerado)
     )
  )
)
