(ns association.facts
  "Industry self-regulatory rule catalog for the General Insurance
  Association of Japan (一般社団法人 日本損害保険協会 / GIAJ, sonpo) -- a
  second industry-association-level source (see cloud-itonami-assoc-6419-jpn-zenginkyo
  for the first) per ADR-2607141700 (cloud-itonami-compliance-fact-federation).
  Aligned to ISIC 6512 (non-life/general insurance), one of the 12
  ISIC-6419-sibling verticals already wired into cloud-itonami-isic-8291's
  compliance-intelligence links. Every entry cites an OFFICIAL sonpo.or.jp
  URL -- never fabricated. A rule not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.

  Both entries below were verified by directly reading the source PDF text
  (not just a search snippet or page title) on 2026-07-14: the 行動規範
  (Code of Conduct) PDF's own cover page shows its 制定/改定 (established/
  revised) date history, and the 独占禁止法遵守指針 PDF's own cover page
  shows its 2026年1月 publication date.")

(def catalog
  "assoc-slug -> vector of self-regulatory rule entries."
  {"sonpo"
   [{:association-rule/id "sonpo.code-of-conduct"
     :association-rule/title "日本損害保険協会 行動規範 (GIAJ Code of Conduct)"
     :association-rule/association "sonpo"
     :association-rule/isic "6512"
     :association-rule/country "JPN"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.sonpo.or.jp/about/guideline/ev7otb0000000cjp-att/action_kodokihan.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/established-date "1991-10-17"
     :association-rule/last-revised-date "2024-03-21"
     :association-rule/retrieved-at "2026-07-14"
     :association-rule/topic #{:ethics :member-conduct :human-rights}}
    {:association-rule/id "sonpo.antitrust-compliance-guideline"
     :association-rule/title "損害保険会社の独占禁止法遵守のための指針 (Guidelines for Antitrust Law Compliance by General Insurance Companies)"
     :association-rule/association "sonpo"
     :association-rule/isic "6512"
     :association-rule/country "JPN"
     :association-rule/kind :self-regulatory-code
     :association-rule/url "https://www.sonpo.or.jp/about/guideline/ev7otb0000000cjp-att/action_dokkinho.pdf"
     :association-rule/url-provenance :official-association-site
     :association-rule/last-revised-date "2026-01"
     :association-rule/retrieved-at "2026-07-14"
     :association-rule/topic #{:antitrust :fair-competition}}]})

(defn spec-basis [assoc-slug] (get catalog assoc-slug))

(defn coverage
  ([] (coverage (keys catalog)))
  ([slugs]
   (let [have (filter catalog slugs)
         missing (remove catalog slugs)]
     {:requested (count slugs)
      :covered (count have)
      :covered-associations (vec (sort have))
      :missing-associations (vec (sort missing))
      :note (str "cloud-itonami-assoc-6512-jpn-sonpo Wave 0 (ADR-2607141700): "
                 (count (get catalog "sonpo")) " sonpo rules seeded with an "
                 "official sonpo.or.jp citation. Extend "
                 "`association.facts/catalog`, never fabricate a rule id/url.")})))

(defn by-topic [assoc-slug topic]
  (filterv #(contains? (:association-rule/topic %) topic) (spec-basis assoc-slug)))
