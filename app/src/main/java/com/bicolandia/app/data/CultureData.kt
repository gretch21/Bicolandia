package com.bicolandia.app.data

private fun qf(vararg pairs: Pair<String, String>) = pairs.map { QuickFact(it.first, it.second) }
private fun hl(vararg pairs: Pair<String, String>) = pairs.map { QuickFact(it.first, it.second) }

val cult_craft = listOf(
  PlaceDetail(
    "cult_craft_0", "Abaca Weaving", "Traditional Crafts", "Catanduanes & Tiwi, Albay", "LIVING CRAFT", "#8D6E63", 4.6f, "+2k",
    "Manila hemp fiber hand-stripped and woven into mats, bags, hats, and rope - a craft that has anchored the Bicol economy for centuries.",
    "Long before synthetic fiber, Bicol built an export economy on abaca - hand-stripped from the plant's stalk, sun-dried, then knotted or woven into rope, mats, bags, and hats. Catanduanes and Tiwi remain centers of the trade today, with entire barangays organized around stripping, drying, and weaving. The craft has survived colonization, wars, and industrialization by continually adapting: modern weavers now sell to fashion and home-decor markets abroad, while the core hand technique hasn't changed in generations.",
    qf("Material" to "Abaca (Manila Hemp) Fiber", "Origin Area" to "Catanduanes & Tiwi, Albay", "Technique" to "Hand-stripping & Handloom Weaving", "Status" to "Living Industry"),
    hl("Cultural Significance" to "Manila hemp fiber hand-stripped and woven into mats, bags, hats, and rope - a craft that has anchored the Bicol economy for centuries."),
    listOf("Abaca fiber markets and cooperatives in Catanduanes", "Tiwi weaving cooperatives, Albay", "Souvenir shops selling abaca products region-wide"), emptyList(), "#8D6E63"
  ),
  PlaceDetail(
    "cult_craft_1", "Pinukpok Cloth", "Traditional Crafts", "Bicol Region", "REVIVAL CRAFT", "#8D6E63", 4.7f, "+3k",
    "A fine, seamless fabric made by pounding abaca fiber until soft, then blending it with cotton, silk, or polyester on a rotary press.",
    "Pinukpok takes its name from the pounding (pukpok) process that turns coarse abaca fiber into something soft enough to wear - a labor-intensive technique nearly lost to mass-produced textiles. Weavers blend the pounded fiber with cotton, silk, or polyester on a rotary press, producing a fabric with abaca's texture but a gentler drape, now used for barongs, bags, and home textiles. NCCA-backed revival programs have helped keep the craft alive by connecting a shrinking pool of skilled weavers with new markets.",
    qf("Material" to "Pounded Abaca Fiber", "Origin Area" to "Bicol Region", "Technique" to "Fiber Pounding & Rotary Press", "Status" to "Revival Craft"),
    hl("Cultural Significance" to "A fine, seamless fabric made by pounding abaca fiber until soft, then blending it with cotton, silk, or polyester on a rotary press."),
    listOf("Bicol textile fairs and trade exhibits", "Local weaving cooperatives producing pinukpok cloth"), emptyList(), "#8D6E63"
  ),
  PlaceDetail(
    "cult_craft_2", "Banig Mat Weaving", "Traditional Crafts", "Camarines Sur & Sorsogon", "HOUSEHOLD CRAFT", "#8D6E63", 4.8f, "+4k",
    "Colorful hand-woven sleeping mats plaited from pandan or seagrass leaves, a household staple across the region.",
    "Long before electric fans, a well-woven banig was how Bicolano households stayed cool - plaited from pandan or seagrass leaves that are dried, dyed, and interlaced into tight geometric patterns. Camarines Sur and Sorsogon are especially known for the craft, where families often divide the work between harvesting, dyeing, and weaving. Beyond bedding, colorful banig have become a go-to souvenir and are increasingly repurposed into wall art, bags, and placemats sold in local markets.",
    qf("Material" to "Pandan / Seagrass Leaves", "Origin Area" to "Camarines Sur & Sorsogon", "Technique" to "Hand Plaiting", "Status" to "Household Craft"),
    hl("Cultural Significance" to "Colorful hand-woven sleeping mats plaited from pandan or seagrass leaves, a household staple across the region."),
    listOf("Banig-weaving villages in Camarines Sur", "Local markets in Sorsogon selling handwoven mats"), emptyList(), "#8D6E63"
  ),
  PlaceDetail(
    "cult_craft_3", "Palayok Pottery", "Traditional Crafts", "Libon, Albay", "FAMILY CRAFT", "#8D6E63", 4.9f, "+5k",
    "Utilitarian clay pots and earthenware hand-shaped from Libon's fine volcanic clay and fired in traditional kilns.",
    "Libon's fine volcanic clay - a legacy of its proximity to Mount Mayon - has made it Bicol's go-to source for handmade earthenware for generations. Potters shape the clay by hand or on a simple wheel, then fire it in open or traditional kilns to produce the palayok pots still used for cooking rice, simmering laing, and storing water in many Bicolano kitchens. The trade is often a family affair, with techniques passed informally from one generation to the next rather than taught in schools.",
    qf("Material" to "Volcanic Clay", "Origin Area" to "Libon, Albay", "Technique" to "Hand-shaping & Kiln Firing", "Status" to "Family-run Craft"),
    hl("Cultural Significance" to "Utilitarian clay pots and earthenware hand-shaped from Libon's fine volcanic clay and fired in traditional kilns."),
    listOf("Pottery-making barangays in Libon, Albay", "Local markets selling palayok and clay cookware"), emptyList(), "#8D6E63"
  ),
  PlaceDetail(
    "cult_craft_4", "Wood Carving & Rattan Furniture", "Traditional Crafts", "Bicol Region", "COTTAGE CRAFT", "#8D6E63", 4.6f, "+6k",
    "Carved wooden furniture and woven rattan pieces shaped by local artisans, sold in souvenir shops region-wide.",
    "Bicol's wood carvers and rattan weavers turn native timber and vine into furniture, home decor, and souvenirs found in shops across the region. The craft draws on materials abundant in Bicol's forests, shaped using techniques passed down through informal apprenticeships rather than formal training. Much of today's output serves the tourist and export furniture markets, though carved santos (religious figures) and household pieces remain common in Bicolano homes.",
    qf("Material" to "Native Wood & Rattan", "Origin Area" to "Region-wide", "Technique" to "Hand Carving & Weaving", "Status" to "Cottage Industry"),
    hl("Cultural Significance" to "Carved wooden furniture and woven rattan pieces shaped by local artisans, sold in souvenir shops region-wide."),
    listOf("Souvenir and furniture shops region-wide", "Local artisan workshops open to visitors"), emptyList(), "#8D6E63"
  ),
  PlaceDetail(
    "cult_craft_5", "Kudyapi (Two-String Lute)", "Traditional Crafts", "Bicol Region", "RARE HERITAGE", "#8D6E63", 4.7f, "+7k",
    "A traditional two-stringed lute, hand-carved from wood, once used to accompany folk songs and the chanting of epics like the Ibalong.",
    "The kudyapi is one of the oldest surviving instruments tied to Bicol's oral traditions - a two-stringed lute hand-carved from a single piece of wood, once used to accompany the chanting of epic poetry and folk songs. Few artisans still know how to carve and tune one properly, making it one of the rarer crafts in the region, kept alive mostly by folk musicians and cultural performers rather than a commercial trade. When it's played today, it's usually as part of a cultural presentation or festival performance rather than everyday use.",
    qf("Material" to "Carved Wood & Fiber Strings", "Origin Area" to "Bicol Region", "Technique" to "Hand-carved Instrument Making", "Status" to "Rare, Kept Alive by Folk Musicians"),
    hl("Cultural Significance" to "A traditional two-stringed lute, hand-carved from wood, once used to accompany folk songs and the chanting of epics like the Ibalong."),
    listOf("Cultural performances during Ibalong Festival, Legazpi City", "Folk music showcases at regional cultural events"), emptyList(), "#8D6E63"
  ),
).map { enrich(it) }

val cult_folklore = listOf(
  PlaceDetail(
    "cult_folklore_0", "The Ibalong Epic", "Folklore & Legends", "Ancient Aslon & Ibalong", "FOUNDATION MYTH", "#6A1B9A", 4.6f, "+2k",
    "A 60-stanza pre-colonial folk epic, first transcribed by Fr. Bernardino de Melendreras, recounting the heroes who tamed the ancient land of Ibalong.",
    "Transcribed in the 19th century by Fr. Bernardino de Melendreras from oral tradition, the Ibalong is Bicol's own creation epic - 60 stanzas recounting how heroes like Baltog, Handiong, and Bantong tamed a land overrun by monsters, floods, and giant beasts. It functions much like a regional origin myth, using allegory to explain the landscape, customs, and character of early Bicolanos. Today the epic lives on mainly through the Ibalong Festival in Legazpi, school curricula, and retellings in local literature and performance.",
    qf("Source" to "Oral Epic (Transcribed 19th c.)", "Era" to "Pre-colonial", "Type" to "Folk Epic", "Best Known For" to "Foundation Myth of Bicol"),
    hl("Cultural Significance" to "A 60-stanza pre-colonial folk epic, first transcribed by Fr. Bernardino de Melendreras, recounting the heroes who tamed the ancient land of Ibalong."),
    listOf("Ibalong Festival street performances, Legazpi City", "Cultural and literary references throughout Albay"), emptyList(), "#6A1B9A"
  ),
  PlaceDetail(
    "cult_folklore_1", "Baltog and the Giant Boar Tandayag", "Folklore & Legends", "Ibalong (Ancient Bicol)", "LEGEND", "#6A1B9A", 4.7f, "+3k",
    "The epic's first hero, said to have wrestled and killed the giant wild boar Tandayag that was ravaging the early settlers' fields.",
    "Baltog is remembered as Ibalong's first civilizer - the hero who wrestled and killed the giant wild boar Tandayag that had been ravaging the fields of early settlers. His victory opens the epic, symbolizing the first triumph of order over wilderness, a struggle later completed by Handiong. The story is often the first Ibalong tale told to children and remains a recurring character in Ibalong Festival performances.",
    qf("Source" to "Ibalong Epic", "Era" to "Pre-colonial", "Type" to "Legend", "Best Known For" to "Bicol's First Civilizer"),
    hl("Cultural Significance" to "The epic's first hero, said to have wrestled and killed the giant wild boar Tandayag that was ravaging the early settlers' fields."),
    listOf("Ibalong Festival performances, Legazpi City"), emptyList(), "#6A1B9A"
  ),
  PlaceDetail(
    "cult_folklore_2", "Handiong the Civilizer", "Folklore & Legends", "Ibalong (Ancient Bicol)", "LEGEND", "#6A1B9A", 4.8f, "+4k",
    "The epic's central hero, credited with bringing law, agriculture, writing, and metalworking to early Bicol after defeating its monsters.",
    "If Baltog opened the epic, Handiong is its central figure - credited with defeating Ibalong's remaining monsters and then, more importantly, bringing law, agriculture, writing, and metalworking to the people. His story shifts the epic from monster-slaying to nation-building, framing him less as a warrior and more as a founder of civilization. Handiong remains the most-referenced figure of the Ibalong tradition, frequently depicted in festival floats, murals, and school retellings across Albay.",
    qf("Source" to "Ibalong Epic", "Era" to "Pre-colonial", "Type" to "Legend", "Best Known For" to "Bringing Civilization to Ibalong"),
    hl("Cultural Significance" to "The epic's central hero, credited with bringing law, agriculture, writing, and metalworking to early Bicol after defeating its monsters."),
    listOf("Ibalong Festival grand parade, Legazpi City"), emptyList(), "#6A1B9A"
  ),
  PlaceDetail(
    "cult_folklore_3", "Bantong and the Beast Rabot", "Folklore & Legends", "Ibalong (Ancient Bicol)", "LEGEND", "#6A1B9A", 4.9f, "+5k",
    "A brave young warrior who single-handedly defeated the half-man, half-beast Rabot, though offered a thousand men to help him.",
    "Offered a thousand men to help him, Bantong chose instead to face the half-man, half-beast Rabot alone - a decision that made him Ibalong's emblem of individual courage over brute force. The tale is shorter than Handiong's saga but is often singled out in retellings for its lesson about self-reliance and quiet confidence. Like the epic's other heroes, Bantong appears in Ibalong Festival reenactments each October.",
    qf("Source" to "Ibalong Epic", "Era" to "Pre-colonial", "Type" to "Legend", "Best Known For" to "Courage & Cunning"),
    hl("Cultural Significance" to "A brave young warrior who single-handedly defeated the half-man, half-beast Rabot, though offered a thousand men to help him."),
    listOf("Ibalong Festival reenactments, Legazpi City"), emptyList(), "#6A1B9A"
  ),
  PlaceDetail(
    "cult_folklore_4", "Oryol the Serpent Enchantress", "Folklore & Legends", "Ibalong (Ancient Bicol)", "LEGEND", "#6A1B9A", 4.6f, "+6k",
    "A beautiful half-woman, half-serpent villain-turned-heroine who fell in love with Handiong and joined his cause against the land's monsters.",
    "Oryol stands apart from Ibalong's other characters as a villain who becomes a hero - a half-woman, half-serpent enchantress who falls for Handiong and turns her power against the very monsters she once aligned with. Her arc gives the epic one of its more nuanced characters, often highlighted in modern retellings as an early example of a complex, morally layered woman in Philippine folklore. She remains a popular figure in Ibalong-themed dance and theater performances.",
    qf("Source" to "Ibalong Epic", "Era" to "Pre-colonial", "Type" to "Legend", "Best Known For" to "A Redemption Story"),
    hl("Cultural Significance" to "A beautiful half-woman, half-serpent villain-turned-heroine who fell in love with Handiong and joined his cause against the land's monsters."),
    listOf("Ibalong Festival cultural shows, Legazpi City"), emptyList(), "#6A1B9A"
  ),
  PlaceDetail(
    "cult_folklore_5", "Daragang Magayon and Mount Mayon", "Folklore & Legends", "Albay", "ORIGIN LEGEND", "#6A1B9A", 4.7f, "+7k",
    "The Bicolano legend of a beautiful maiden whose tragic love story is said to explain the birth of Mayon Volcano's perfect symmetrical cone.",
    "Every Bicolano grows up hearing some version of Daragang Magayon's story - a beautiful maiden whose tragic love, in different tellings involving a suitor and a rival warrior, is said to explain why Mayon Volcano rises in such a perfect, symmetrical cone. Unlike the Ibalong epic, this is a standalone origin legend tied specifically to the volcano's shape, and it's the story locals most often point to when asked why Mayon looks the way it does. The name 'Daragang Magayon' (beautiful maiden) has since become shorthand for Albay itself, lending its name to parks, festivals, and civic landmarks.",
    qf("Source" to "Bicolano Oral Legend", "Era" to "Pre-colonial", "Type" to "Legend", "Best Known For" to "Origin Story of Mayon Volcano"),
    hl("Cultural Significance" to "The Bicolano legend of a beautiful maiden whose tragic love story is said to explain the birth of Mayon Volcano's perfect symmetrical cone."),
    listOf("Views of Mayon Volcano from Daraga, Albay", "Legazpi City parks and landmarks named after the legend"), emptyList(), "#6A1B9A"
  ),
).map { enrich(it) }

val cult_figure = listOf(
  PlaceDetail(
    "cult_figure_0", "Simeón Ola", "Historical Figures", "Guinobatan, Albay", "REVOLUTIONARY HERO", "#37474F", 4.6f, "+2k",
    "The last Filipino general to surrender to American forces, holding out in the Bicol mountains until September 1903.",
    "When Simeón Ola finally surrendered to American forces in September 1903, he wasn't just laying down arms - he was closing the final chapter of organized Filipino resistance in the Philippine-American War, having held out in the Bicol mountains long after most other revolutionary leaders had capitulated. Born in Guinobatan, Albay, Ola became a guerrilla commander during the revolution against Spain and kept fighting American occupation for years afterward. His stand is commemorated today through Camp Simeon A. Ola, the PNP regional headquarters in Legazpi City named in his honor.",
    qf("Lived" to "1865 – 1952", "Hometown" to "Guinobatan, Albay", "Known For" to "Last General to Surrender", "Legacy" to "Camp Simeon A. Ola named in his honor"),
    hl("Cultural Significance" to "The last Filipino general to surrender to American forces, holding out in the Bicol mountains until September 1903."),
    listOf("Camp Simeon A. Ola, Legazpi City", "Historical markers in Guinobatan, Albay"), emptyList(), "#37474F"
  ),
  PlaceDetail(
    "cult_figure_1", "Wenceslao Vinzons", "Historical Figures", "Camarines Norte", "WWII HERO", "#37474F", 4.7f, "+3k",
    "A lawyer, youngest-ever congressman, and guerrilla leader executed by Japanese forces during WWII for leading Bicol's resistance.",
    "Wenceslao Vinzons packed an extraordinary amount into just 32 years - becoming the country's youngest-ever congressman, founding the Young Philippines political party, and later leading Bicol's guerrilla resistance against Japanese occupation during WWII. His refusal to cooperate with Japanese forces ultimately cost him his life; he was executed in 1942 along with members of his family. The town of Indan in Camarines Norte was renamed Vinzons in his honor, and he remains one of Bicol's most memorialized wartime heroes.",
    qf("Lived" to "1910 – 1942", "Hometown" to "Indan (now Vinzons), Camarines Norte", "Known For" to "WWII Resistance Leader", "Legacy" to "Town of Vinzons named in his honor"),
    hl("Cultural Significance" to "A lawyer, youngest-ever congressman, and guerrilla leader executed by Japanese forces during WWII for leading Bicol's resistance."),
    listOf("Vinzons, Camarines Norte (town renamed in his honor)", "Historical markers and monuments in Camarines Norte"), emptyList(), "#37474F"
  ),
  PlaceDetail(
    "cult_figure_2", "Elías Angeles", "Historical Figures", "Camarines Sur", "REVOLUTIONARY LEADER", "#37474F", 4.8f, "+4k",
    "A revolutionary leader who took part in the 1898 uprisings against Spanish rule in Camarines Sur.",
    "Elías Angeles was among the local leaders who answered the Katipunan's call in Camarines Sur, joining the wave of uprisings against Spanish rule that spread through Bicol in 1898. Detailed records of his individual campaigns are sparse compared to more nationally prominent revolutionaries, but he is remembered locally alongside Félix Plazo as part of the province's revolutionary leadership. Their efforts contributed to the broader collapse of Spanish colonial control across the Bicol region that year.",
    qf("Lived" to "19th century", "Hometown" to "Camarines Sur", "Known For" to "Revolutionary Leadership", "Legacy" to "Honored among Bicol's 1896 revolutionaries"),
    hl("Cultural Significance" to "A revolutionary leader who took part in the 1898 uprisings against Spanish rule in Camarines Sur."),
    listOf("Historical markers commemorating the 1898 uprisings, Camarines Sur"), emptyList(), "#37474F"
  ),
  PlaceDetail(
    "cult_figure_3", "Félix Plazo", "Historical Figures", "Camarines Sur", "REVOLUTIONARY LEADER", "#37474F", 4.9f, "+5k",
    "A notable figure in the revolutionary uprisings that shook Camarines Sur during the Philippine Revolution.",
    "Félix Plazo's name is tied closely to the 1898 uprisings that swept through Camarines Sur as Spanish colonial authority collapsed across Bicol. He is remembered locally as part of the province's revolutionary leadership alongside Elías Angeles, part of a broader wave of Bicolano leaders who took up arms in the final years of Spanish rule. As with many provincial revolutionary figures, much of what's known about him today comes through local commemoration rather than national record.",
    qf("Lived" to "19th century", "Hometown" to "Camarines Sur", "Known For" to "Revolutionary Leadership", "Legacy" to "Remembered alongside Elías Angeles"),
    hl("Cultural Significance" to "A notable figure in the revolutionary uprisings that shook Camarines Sur during the Philippine Revolution."),
    listOf("Historical markers commemorating the 1898 uprisings, Camarines Sur"), emptyList(), "#37474F"
  ),
  PlaceDetail(
    "cult_figure_4", "General Vito Belarmino", "Historical Figures", "Assigned to Bicol Region", "ZONE COMMANDER", "#37474F", 4.6f, "+6k",
    "Zone Commander of the Revolutionary Forces in Bicol, who led the victorious Battle of Camalig in April 1898.",
    "As Zone Commander of the Revolutionary Forces in Bicol, Vito Belarmino led what became one of the region's most significant victories against Spain - the Battle of Camalig in April 1898, which broke Spanish military control in Albay. The win helped accelerate the collapse of colonial authority across the wider Bicol region in the months that followed. Belarmino's leadership during the battle is still cited in local histories as a turning point for the revolution in southern Luzon.",
    qf("Lived" to "19th – 20th century", "Hometown" to "Assigned to Bicol Region", "Known For" to "Zone Commander, Battle of Camalig", "Legacy" to "First major revolutionary victory in Bicol"),
    hl("Cultural Significance" to "Zone Commander of the Revolutionary Forces in Bicol, who led the victorious Battle of Camalig in April 1898."),
    listOf("Camalig, Albay - site of the 1898 battle", "Historical markers in Camalig town"), emptyList(), "#37474F"
  ),
  PlaceDetail(
    "cult_figure_5", "José Ma. Panganiban", "Historical Figures", "Mahinang, Camarines Norte", "PROPAGANDA MOVEMENT", "#37474F", 4.7f, "+7k",
    "A reformist writer and Propaganda Movement figure who wrote for La Solidaridad, honored as one of Bicol's national heroes.",
    "Only 27 when he died, José Ma. Panganiban packed a career's worth of advocacy into a short life - writing for La Solidaridad alongside figures like Rizal and del Pilar as part of the Propaganda Movement pushing for reforms under Spanish rule. Though he died before the revolution reached its peak, his writing helped shape the intellectual groundwork that fed into it. His hometown of Mahinang, Camarines Norte was later renamed Panganiban in his honor, and he is counted among Bicol's most recognized national heroes.",
    qf("Lived" to "1863 – 1890", "Hometown" to "Mahinang, Camarines Norte", "Known For" to "Propaganda Movement Writer", "Legacy" to "Town of Panganiban renamed in his honor"),
    hl("Cultural Significance" to "A reformist writer and Propaganda Movement figure who wrote for La Solidaridad, honored as one of Bicol's national heroes."),
    listOf("Panganiban, Camarines Norte (town renamed in his honor)", "Historical markers and monuments in Camarines Norte"), emptyList(), "#37474F"
  ),
).map { enrich(it) }

val cult_tradition = listOf(
  PlaceDetail(
    "cult_tradition_0", "Paninilbihan (Courtship Service)", "Living Traditions", "Bicol Region", "COURTSHIP CUSTOM", "#C62828", 4.6f, "+2k",
    "A traditional courtship custom where a suitor performs household chores and labor for the family of the woman he is courting.",
    "Paninilbihan asked a lot of a suitor - weeks or months of chopping wood, fetching water, and doing farm labor for a woman's family, all to prove he was worth marrying into. The custom functioned as an extended, very literal courtship trial, giving families time to judge a suitor's work ethic and character before consenting to marriage. It has largely faded as courtship norms modernized, but older generations in rural Bicol still recall it, and it's occasionally referenced in local retellings of Bicolano wedding customs.",
    qf("Category" to "Courtship Custom", "Practiced In" to "Rural Bicol Communities", "Occasion" to "Courtship", "Status" to "Fading Tradition"),
    hl("Cultural Significance" to "A traditional courtship custom where a suitor performs household chores and labor for the family of the woman he is courting."),
    listOf("Referenced in local Bicolano wedding traditions and folklore talks"), emptyList(), "#C62828"
  ),
  PlaceDetail(
    "cult_tradition_1", "Pantomina Folk Dance", "Living Traditions", "Sorsogon & Region-wide", "FOLK DANCE", "#C62828", 4.7f, "+3k",
    "A lively courtship dance mimicking a hen and rooster, performed at weddings and showcased during the Kasanggayahan Festival.",
    "Pantomina is Bicol's answer to a courtship dance - two performers mimicking a hen being courted by a rooster, complete with playful strutting, teasing, and coy avoidance before the eventual 'catch.' It's a fixture at Bicolano weddings, where guests often join in, and gets a bigger stage during Sorsogon's Kasanggayahan Festival, where troupes perform more elaborate, costumed versions. The dance's comic, flirtatious energy has kept it popular even as more formal folk dances have faded from everyday celebrations.",
    qf("Category" to "Folk Dance", "Practiced In" to "Sorsogon & Region-wide", "Occasion" to "Weddings & Festivals", "Status" to "Actively Performed"),
    hl("Cultural Significance" to "A lively courtship dance mimicking a hen and rooster, performed at weddings and showcased during the Kasanggayahan Festival."),
    listOf("Kasanggayahan Festival performances, Sorsogon City", "Performed at weddings across the Bicol region"), emptyList(), "#C62828"
  ),
  PlaceDetail(
    "cult_tradition_2", "Sarung Banggi (Folk Song)", "Living Traditions", "Bicol Region", "FOLK SONG", "#C62828", 4.8f, "+4k",
    "One of the most beloved Bicolano folk songs - a gentle serenade about a peaceful, moonlit evening.",
    "If there's one song every Bicolano can hum, it's Sarung Banggi - a gentle, wistful serenade describing a quiet, moonlit evening that has become almost a second anthem for the region. Its simple melody has made it a staple at gatherings, competitions, and cultural showcases, and it's often the first Bikol-language song taught to children learning about their heritage. Countless Bicolano singers and choirs have recorded their own versions, keeping it very much alive rather than preserved only as a historical piece.",
    qf("Category" to "Folk Song", "Practiced In" to "Region-wide", "Occasion" to "Serenades & Gatherings", "Status" to "Bicol's Best-loved Folk Song"),
    hl("Cultural Significance" to "One of the most beloved Bicolano folk songs - a gentle serenade about a peaceful, moonlit evening."),
    listOf("Performed at cultural festivals and gatherings region-wide"), emptyList(), "#C62828"
  ),
  PlaceDetail(
    "cult_tradition_3", "Bicol Language & Rawitdawit Poetry", "Living Traditions", "Bicol Region", "LANGUAGE & LITERATURE", "#C62828", 4.9f, "+5k",
    "The Bikol language and its tradition of rawitdawit (poetry) and harana (serenade verse), still spoken and composed today.",
    "Bikol isn't a single dialect but a language family, with distinct variations spoken across Albay, Camarines Sur, Camarines Norte, Sorsogon, Catanduanes, and Masbate, loosely unified by shared vocabulary and rhythm. Its poetic tradition, rawitdawit, and its serenade verse form, harana, have long been vehicles for courtship, storytelling, and public expression, composed both formally and improvised on the spot. Despite the spread of Filipino and English in schools and media, Bikol remains actively spoken at home and in local media across the region, with a growing push to preserve it in literature and education.",
    qf("Category" to "Language & Literature", "Practiced In" to "Region-wide", "Occasion" to "Everyday & Ceremonial Speech", "Status" to "Widely Spoken"),
    hl("Cultural Significance" to "The Bikol language and its tradition of rawitdawit (poetry) and harana (serenade verse), still spoken and composed today."),
    listOf("Spoken region-wide - heard in local markets, radio, and daily life"), emptyList(), "#C62828"
  ),
  PlaceDetail(
    "cult_tradition_4", "Kagharong (Traditional Bicolano House)", "Living Traditions", "Rural Bicol Towns", "VERNACULAR ARCHITECTURE", "#C62828", 4.6f, "+6k",
    "Raised nipa-and-bamboo homes built with wide eaves and stilts, designed to withstand Bicol's heat, rain, and typhoons.",
    "A kagharong's design is basically a direct response to Bicol's climate - raised on stilts to avoid flooding, built from lightweight nipa and bamboo that flexes rather than breaks in strong winds, and topped with wide eaves to shed the region's heavy rains. The open, elevated layout also kept homes cooler in the tropical heat long before electric fans or air conditioning. Concrete construction has replaced most of these homes in towns and cities, but a shrinking number still stand in rural barangays, and the basic design still influences how some newer Bicolano homes are built.",
    qf("Category" to "Vernacular Architecture", "Practiced In" to "Rural Bicol Towns", "Occasion" to "Everyday Life", "Status" to "Increasingly Rare"),
    hl("Cultural Significance" to "Raised nipa-and-bamboo homes built with wide eaves and stilts, designed to withstand Bicol's heat, rain, and typhoons."),
    listOf("Rural barangays across Bicol still featuring traditional kagharong homes"), emptyList(), "#C62828"
  ),
  PlaceDetail(
    "cult_tradition_5", "Voyadores of the Peñafrancia", "Living Traditions", "Naga City", "RELIGIOUS TRADITION", "#C62828", 4.7f, "+7k",
    "The all-male devotees who carry the image of Our Lady of Peñafrancia during the fluvial procession, a role passed down through generations.",
    "Being a voyador is considered one of the highest honors a Bicolano Catholic man can hold - carrying the image of Our Lady of Peñafrancia on their shoulders during the fluvial procession down the Bicol River each September. The role is exclusively male, often passed down through families, with voyadores preparing for months before the festival. The sheer number of devotees who apply to carry the image, far more than the procession has room for, says a lot about how central the Peñafrancia devotion remains to Naga's identity.",
    qf("Category" to "Religious Tradition", "Practiced In" to "Naga City", "Occasion" to "Peñafrancia Festival", "Status" to "Actively Practiced"),
    hl("Cultural Significance" to "The all-male devotees who carry the image of Our Lady of Peñafrancia during the fluvial procession, a role passed down through generations."),
    listOf("Peñafrancia fluvial procession, Bicol River, Naga City", "Basilica Minore of Our Lady of Peñafrancia, Naga City"), emptyList(), "#C62828"
  ),
).map { enrich(it) }

val cult_indig = listOf(
  PlaceDetail(
    "cult_indig_0", "Agta Indigenous Communities", "Indigenous Heritage", "Camarines Norte, Camarines Sur & Sorsogon", "LIVING COMMUNITY", "#00796B", 4.6f, "+2k",
    "Negrito hunter-gatherer communities among the earliest inhabitants of the Bicol Peninsula, with communities still present today.",
    "The Agta are believed to be among the earliest inhabitants of the Bicol Peninsula, predating the Austronesian-speaking settlers who make up most of the region's population today. Traditionally hunter-gatherers, Agta communities have historically moved with the seasons through the forests and coastal areas of Camarines Norte, Camarines Sur, and Sorsogon, relying on hunting, fishing, and forest-gathering. Many communities today balance traditional practices with settled livelihoods, facing ongoing challenges around land rights and access to services, while cultural and advocacy groups work to support them.",
    qf("Group / Belief" to "Agta (Aeta) People", "Found In" to "Camarines Norte, Camarines Sur, Sorsogon", "Significance" to "Region's Earliest Inhabitants", "Status" to "Living Communities"),
    hl("Cultural Significance" to "Negrito hunter-gatherer communities among the earliest inhabitants of the Bicol Peninsula, with communities still present today."),
    listOf("Agta communities in upland Camarines Norte, Camarines Sur, and Sorsogon"), emptyList(), "#00796B"
  ),
  PlaceDetail(
    "cult_indig_1", "Gabâ (Belief in Cosmic Retribution)", "Indigenous Heritage", "Bicol Region", "FOLK BELIEF", "#00796B", 4.7f, "+3k",
    "A Bicolano folk belief that wrongdoing or disrespect toward nature, elders, or the vulnerable invites misfortune in return.",
    "Gabâ is less a formal religion than a deeply ingrained moral compass - the belief that disrespecting nature, elders, or the vulnerable invites misfortune back onto you, sooner or later. It shows up in everyday Bicolano speech as a warning aimed at discouraging cruelty, wastefulness, or arrogance, functioning almost like a folk version of karma. Even among younger, more urbanized Bicolanos, the concept still surfaces in conversation, showing how folk belief systems can outlast the specific practices that originally carried them.",
    qf("Group / Belief" to "Folk Belief System", "Found In" to "Region-wide", "Significance" to "Moral & Environmental Code", "Status" to "Still Held by Many"),
    hl("Cultural Significance" to "A Bicolano folk belief that wrongdoing or disrespect toward nature, elders, or the vulnerable invites misfortune in return."),
    listOf("A belief still referenced in everyday conversation across the region"), emptyList(), "#00796B"
  ),
  PlaceDetail(
    "cult_indig_2", "Pre-Hispanic Barangay Governance", "Indigenous Heritage", "Coastal & Riverine Bicol", "HISTORICAL", "#00796B", 4.8f, "+4k",
    "Early Bicolano communities organized under a datu and customary law, long before Spanish colonization introduced new systems.",
    "Long before Spanish colonization introduced provinces and municipalities, coastal and riverine Bicolano communities organized themselves under a datu - a chief who held authority alongside customary law and community consensus. These early barangays functioned as largely self-governing units, trading with neighboring settlements and, in some cases, with other parts of maritime Southeast Asia. While the political structure itself didn't survive colonization intact, the word 'barangay' - and something of its communal spirit - persists as the basic unit of local governance in the Philippines today.",
    qf("Group / Belief" to "Pre-colonial Governance", "Found In" to "Coastal & Riverine Bicol", "Significance" to "Root of Local Leadership Customs", "Status" to "Historical"),
    hl("Cultural Significance" to "Early Bicolano communities organized under a datu and customary law, long before Spanish colonization introduced new systems."),
    listOf("Referenced in regional history exhibits and local historical markers"), emptyList(), "#00796B"
  ),
  PlaceDetail(
    "cult_indig_3", "The Image of Our Lady of Peñafrancia (Ina)", "Indigenous Heritage", "Naga City", "MARIAN DEVOTION", "#00796B", 4.9f, "+5k",
    "A centuries-old statue, carved at the request of Fr. Miguel Robles, that is central to Bicol's most important Marian devotion.",
    "Carved at the request of Fr. Miguel Robles in the 1700s after he recovered from a serious illness, the image of Our Lady of Peñafrancia - affectionately called 'Ina' (Mother) by Bicolanos - has grown into the most venerated Marian devotion in the region. Her feast draws hundreds of thousands of devotees to Naga City each September for a nine-day festival capped by a dramatic fluvial procession down the Bicol River. For many Bicolanos, devotion to Ina is inseparable from regional identity itself, celebrated by Catholics and non-Catholics alike as a marker of what it means to be Bicolano.",
    qf("Group / Belief" to "Marian Devotion", "Found In" to "Naga Metropolitan Cathedral / Basilica", "Significance" to "Patroness of Bicolandia", "Status" to "Actively Venerated"),
    hl("Cultural Significance" to "A centuries-old statue, carved at the request of Fr. Miguel Robles, that is central to Bicol's most important Marian devotion."),
    listOf("Basilica Minore of Our Lady of Peñafrancia, Naga City", "Naga Metropolitan Cathedral", "Peñafrancia fluvial procession route, Bicol River"), emptyList(), "#00796B"
  ),
  PlaceDetail(
    "cult_indig_4", "Bicolano Anting-Anting & Folk Healing", "Indigenous Heritage", "Rural Bicol Communities", "FOLK MEDICINE", "#00796B", 4.6f, "+6k",
    "Amulets and herbal folk-healing traditions practiced by albularyo (folk healers), often alongside Catholic faith.",
    "Long before - and often alongside - modern medicine, Bicolano communities relied on albularyo (folk healers) who combined herbal remedies, prayer, and anting-anting (amulets believed to carry protective or healing power). Practices range from herbal poultices for common ailments to ritual healing for conditions believed to have spiritual causes, blending pre-colonial animist beliefs with centuries of Catholic influence. The tradition has faded significantly with access to formal healthcare, but albularyo can still be found in some rural communities, particularly for ailments locals believe medicine alone can't fully explain.",
    qf("Group / Belief" to "Folk Healing Tradition", "Found In" to "Rural Bicol Communities", "Significance" to "Traditional Medicine & Protection", "Status" to "Practiced in Some Areas"),
    hl("Cultural Significance" to "Amulets and herbal folk-healing traditions practiced by albularyo (folk healers), often alongside Catholic faith."),
    listOf("Practiced in some rural Bicol communities"), emptyList(), "#00796B"
  ),
  PlaceDetail(
    "cult_indig_5", "Bicol's Austronesian Roots", "Indigenous Heritage", "Coastal & Inland Bicol", "ARCHAEOLOGICAL", "#00796B", 4.7f, "+7k",
    "Pottery, tools, and jewelry unearthed along Bicol's coastlines point to early Austronesian-speaking settlers who shaped the region's roots.",
    "Pottery shards, stone tools, and jewelry unearthed along Bicol's coastlines trace back to Austronesian-speaking seafarers who settled the region thousands of years ago, part of one of history's great maritime migrations that populated islands from Madagascar to Easter Island. These early settlers brought agriculture, boat-building, and pottery techniques that laid the groundwork for the communities that would later develop into Ibalong and, eventually, modern Bicol. Archaeological finds across the region continue to add detail to this deep ancestral timeline, connecting present-day Bicolanos to a migration story shared with much of the Pacific.",
    qf("Group / Belief" to "Austronesian Heritage", "Found In" to "Coastal & Inland Bicol", "Significance" to "Deep Ancestral Roots", "Status" to "Archaeological Record"),
    hl("Cultural Significance" to "Pottery, tools, and jewelry unearthed along Bicol's coastlines point to early Austronesian-speaking settlers who shaped the region's roots."),
    listOf("Archaeological sites and artifact collections across coastal Bicol"), emptyList(), "#00796B"
  ),
).map { enrich(it) }

val cultureData: Map<String, List<PlaceDetail>> = mapOf(
  "crafts" to cult_craft, "folklore" to cult_folklore, "figures" to cult_figure,
  "traditions" to cult_tradition, "indigenous" to cult_indig
)
