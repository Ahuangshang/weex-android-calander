<template>
  <!--端午节-->
  <scroller>
    <div>
      <image :style="{ width: img_w_top, height:img_1_h }"
             src="http://imengu.cn/Ahuangshang/img/dragonBoatFestival/dragonBoat_1.jpg"
             placeholder="http://imengu.cn/Ahuangshang/img/image_icon/default.png"></image>
      <div :style="{ marginLeft: font(contentMargin),marginRight: font(contentMargin)}">
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=title>
        </text>
        <text :style="{ fontSize: font(tex_size_2) ,marginTop:font(contentMargin)}"> 起源</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_1>
        </text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_2>
        </text>
        <image :style="{ width: img_w, height:img_2_h }"
               src="http://imengu.cn/Ahuangshang/img/dragonBoatFestival/dragonBoat_2.jpg"
               placeholder="http://imengu.cn/Ahuangshang/img/image_icon/default.png"></image>
        <text :style="{ fontSize: font(tex_size_2)}" style="margin-top: 15px"> 历史渊源</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_3>
        </text>
        <image :style="{ width: img_w, height:img_4_h }"
               src="http://imengu.cn/Ahuangshang/img/dragonBoatFestival/dragonBoat_4.jpg"
               placeholder="http://imengu.cn/Ahuangshang/img/image_icon/default.png"></image>
        <text :style="{ fontSize: font(tex_size_2)}" style="margin-top: 15px"> 民俗活动</text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 扒龙舟</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_4>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 挂艾草与菖蒲</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_5>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 端午食粽</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_6>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 放纸鸢</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_7>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 草药水（沐兰汤）
        </text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_8>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 拴五色丝线</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_9>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 打午时水</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_10>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 铸阳燧</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_11>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 浸龙舟水</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_12>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 佩豆娘</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_13>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 画额</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_14>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 避五毒</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_15>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 采药、制凉茶</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_16>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 饮蒲酒、雄黄酒、朱砂酒
        </text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_17>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 端午雨</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_18>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 跳钟馗</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_19>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 斗草</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_20>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 打马球</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_21>
        </text>
        <text :style="{ fontSize: font(18)}" style="margin-top: 15px;margin-left: 9px;font-weight: bold"> 九狮拜象</text>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_22>
        </text>
        <text :style="{ fontSize: font(tex_size_2)}" style="margin-top: 15px">传统饮食</text>
        <image :style="{ width: img_w, height:img_5_h }"
               src="http://imengu.cn/Ahuangshang/img/dragonBoatFestival/dragonBoat_5.jpg"
               placeholder="http://imengu.cn/Ahuangshang/img/image_icon/default.png"></image>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_23>
        </text>
        <image :style="{ width: img_w, height:img_6_h }"
               src="http://imengu.cn/Ahuangshang/img/dragonBoatFestival/dragonBoat_6.jpg"
               placeholder="http://imengu.cn/Ahuangshang/img/image_icon/default.png"></image>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_24>
        </text>
        <image :style="{ width: img_w, height:img_7_h }"
               src="http://imengu.cn/Ahuangshang/img/dragonBoatFestival/dragonBoat_7.jpg"
               placeholder="http://imengu.cn/Ahuangshang/img/image_icon/default.png"></image>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_25>
        </text>
        <image :style="{ width: img_w, height:img_8_h }"
               src="http://imengu.cn/Ahuangshang/img/dragonBoatFestival/dragonBoat_8.jpg"
               placeholder="http://imengu.cn/Ahuangshang/img/image_icon/default.png"></image>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_26>
        </text>
        <image :style="{ width: img_w, height:img_9_h }"
               src="http://imengu.cn/Ahuangshang/img/dragonBoatFestival/dragonBoat_9.jpg"
               placeholder="http://imengu.cn/Ahuangshang/img/image_icon/default.png"></image>
        <text
            :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
            style="color: #334f16;" :value=content_27>
        </text>
      </div>
    </div>
  </scroller>
</template>

<script>
  const util = require('../js/methods.js');
  export default {
    name: "dragonBoatFestival",
    props: {
      img_w_top: {
        default: 750
      },
      img_w: {
        default: 718
      },
      img_1_h: {
        default: 343.5
      },
      img_2_h: {
        default: 442
      },

      img_3_h: {
        default: 504
      },

      img_4_h: {
        default: 548
      },
      img_5_h: {
        default: 429
      },
      img_6_h: {
        default: 429
      },
      img_7_h: {
        default: 424
      },
      img_8_h: {
        default: 430
      },
      img_9_h: {
        default: 430
      },
      contentMargin: {
        default: 7
      },
      tex_size_1: {
        default: 16
      },
      tex_size_2: {
        default: 22
      },
      topMargin: {
        default: 18
      },
      line_height: {
        default: 30
      },
      title: {
        default: "       端午节，又称端阳节、重午节、龙日、龙舟节、正阳节、浴兰节、天中节等等，是中国民间传统节日。端午节源自天象崇拜，由上古时代龙图腾祭祀演变而来。仲夏端午，苍龙七宿飞升至正南中天，是龙飞天的日子，即如《易经·乾卦》第五爻的爻辞曰：“飞龙在天”；此时龙星既“得中”又“得正”，处在大吉之位，恩施普也，龙德显扬。端午节起源涵盖了古老星象文化、人文哲学等方面内容，蕴含着深邃丰厚的文化内涵。端午节在传承发展中杂揉了多种民俗为一体，节俗内容丰富，扒龙舟与食粽是端午节的两大礼俗，这两大传统礼俗在中国自古传承，至今不辍。"
      },
      content_1: {
        default: "       起源于中国，最初是上古先民以龙舟竞渡形式祭祀龙祖的节日。因传说战国时期的楚国诗人屈原在端午抱石跳汨罗江自尽，后来人们亦将端午节作为纪念屈原的节日；个别地方也有纪念伍子胥、曹娥及介子推等说法。总的来说，端午节起源于江浙地区吴越部族的龙图腾祭祀，注入夏季时令“祛病防疫”风尚，把端午视为“恶月恶日”起于北方，附会纪念屈原等历史人物纪念内容，最后形成如今端午节文化内涵。\n" +
          "       端午节与春节、清明节、中秋节并称为中国民间四大传统节日。端午文化在世界上影响广泛，世界上一些国家和地区也有庆贺端午的活动。2006年5月，国务院将其列入首批国家级非物质文化遗产名录；自2008年起，被列为国家法定节假日。2009年9月，联合国教科文组织正式批准将其列入《人类非物质文化遗产代表作名录》，端午节成为中国首个入选世界非遗的节日。"
      },
      content_2: {
        default: "       端午祭龙礼俗的形成与原始信仰、祭祀文化、干支历法以及苍龙七宿正处南中的天象有关。中国古代的星象文化源远流长、博大精深，古人很早开始就探索宇宙的奥秘，并由此演绎出了一套完整深奥的观星文化。《春秋命历序》：“天地开辟，万物浑浑，无知无识；阴阳所凭，天体始于北极之野…日月五纬一轮转；天皇出焉…定天之象，法地之仪，作干支以定日月度。”上古时代人们定天之象、法地之仪，根据日月星辰的运行轨迹和位置，将黄道和赤道附近的区域分作28组星宿，俗称“二十八宿”，按东南西北四方各分为七宿，即为“四象”；在东方的“角、亢、氐、房、心、尾、箕”组成一个完整的龙形星象，即为“苍龙七宿”。苍龙七宿的出没周期与一年四时周期相一致。\n" +
          "       在传统文化中，方位和时间以及八卦是联系在一起的。先天八卦以乾坤定南北，天南地北为序，上为天为乾，下为地为坤；正南为先天八卦的乾位，即为“天”。仲夏午月午日，苍龙七宿运行至正南中（乾）方位，是龙升天的日子。苍龙群星一年四时的运行情况及事象规律，在《易经·乾卦》的爻辞中有阐发，仲夏端午苍龙运行至正南中天方位，对应乾卦第五爻“飞龙在天”。在《易经》中，以八卦为基础又两两重合而构成六十四重卦；重卦乾由上下两个乾卦重合而成，它的卦形由六根阳爻组成；爻是要从下往上数，而阳爻又以“九”代称，故从下数第五爻称“九五”；九五之爻在上乾卦中居于中的位置，称“得中”，而且从总卦来看，它处于奇数的位置，阳爻处于奇位称“得正”，故九五爻既“得中”又“得正”，从其所处位置来看，就是大吉之位。\n" +
          "       仲夏端午苍龙七宿飞升于正南中天，《易经》称其为“飞龙在天”，此时龙星处在“中正”之位，既“得中”又“得正”，为大吉大利之象。龙是古越人的原始信仰，古越人以龙作为部族图腾神、保护神，自比是龙的子孙，他们不仅有“断发文身”以“像龙子”的习俗，而且在每年“飞龙在天”的仲夏端午举行盛大图腾祭，酬谢龙祖恩德，祈福纳祥、压邪攘灾。古代的祭仪情形虽渺茫难晓，但还是可以从后世的节仪中寻找到一些古俗遗迹。端午文化充分体现了中华民族先祖们祗敬感德神祖的人文精神以及追求“天、地、人”的和谐合一，讲究顺应天时地宜、遵循自然规律的思想。端午文化作为中华传统文化的重要组成部分，反映了中华文化源远流长、博大精深。"
      },
      content_3: {
        default: "        古老传统节日的起源与上古原始信仰、祭祀文化及天象、历法等人文与自然文化内容有关。根据现代人类学、考古学的研究成果，人类最原始的两种信仰：一是天地信仰，二是祖先信仰。古老传统节日多数形成于古人择吉日祭祀，以谢天地神灵、祖先恩德的活动。早期的节日文化，反映的是古人自然崇拜、固本思源等人文精神；一系列的祭祀活动，则蕴含着祗敬感德、礼乐文明深邃文化内涵。节日的起源和发展是一个逐渐形成，潜移默化地完善与普及的过程。古时南北风俗各异，先秦时代端午节的节俗活动鲜见于中原文献记载，就现存文献没法直接考证其源流。关于端午节的相关文字记载，“端午”二字，最早出现在晋代的《风土记》中，但端午的习俗却早已有之，譬如龙舟竞渡祭祀之俗，早已存在。关于端午节的历史起源，学术界主要有四种观点：\n" +
          "       1、流传甚广的“历史人物纪念”观点，依据南朝梁人吴均的《续齐谐记》（神话志怪小说）及宗檩的《荆楚岁时记》，认为端午节起源于纪念屈原。还有认为是纪念伍子胥、纪念曹娥等。\n" +
          "       2、闻一多先生认为端午节是吴、越举行龙图腾崇拜活动的节日，他在《端午考》及《端午节的历史教育》（见《闻一多全集》）两篇论文中详细论证了他的观点。\n" +
          "       3、认为端午节起源于“恶月恶日”见张心勤《端午节非因屈原考》）。\n" +
          "       4、认为端午节源于夏至（详见刘德谦《“端午”始源又一说》）。\n"
      },
      content_4: {
        default: "        扒龙舟是端午节的一项重要活动，古时盛行于吴、越、楚，现在我国南方沿海一带十分流行。传出国外后，深受各国人民的喜爱并形成了国际比赛。据《河姆渡遗址第一期发掘报告》称，早在7000年前，远古先民已用独木刳成木舟，并加上木桨划舟。“龙舟”一词，最早见于《穆天子传》卷五：“天子乘鸟舟、龙舟浮于大沼。”预《九歌·湘君》中“驾飞龙今北征，邅吾道兮洞庭”，“石濑浅浅，飞龙兮翩翩”。学者们认为文中的“飞龙”即是龙舟。\n" +
          "       扒龙舟历史悠久，是多人集体划桨竞赛。龙舟竞渡分为请龙、祭龙神、游龙和收龙等几个版块。龙舟竞渡前一般都要举行隆重的祭祀仪式，先要请龙、祭神。如广东龙舟，在端午前要择吉日从水下起出，祭过神后，安上龙头、龙尾，再准备竞渡。闽、台则往妈祖庙祭拜。在过去，人们祭祀龙神时气氛很严肃，多祈求福佑、风调雨顺、去邪祟、攘灾异、事事如意。在湖北的屈原家乡秭归，也有划龙舟祭拜屈原的仪式流传。"
      },
      content_5: {
        default: "        在端午节布置种种可驱邪祛病的花草，来源亦久。人们把插艾草和菖蒲作为端午节重要内容之一。如挂艾草于门，《荆楚岁时记》：“采艾以为人，悬门户上，以禳毒气。”这是由于艾为重要的药用植物，又可制艾绒治病，灸穴，又可驱虫。五月艾含艾油最多，（此时正值文生长旺期）所以功效最好，人们也就争相采艾了。往往会在家门口挂几株艾草，由于艾草特殊的香味，人们用它来驱病、防蚊、辟邪。\n" +
          "        “艾”，又名家艾、艾蒿，它的茎、叶都含有挥发性芳香油，所产生的奇特芳香，可驱蚊蝇、虫蚁，净化空气。菖蒲的叶片也含有挥发性芳香油，是提神通窍、健骨消滞、杀虫灭菌的药物。有关艾草可以驱邪的传说已经流传很久，主要是它具备医药的功能而来，如宗懔的《荆楚岁时记》中曰：“鸡未鸣时，采艾似人形者，揽而取之，收以灸病，甚验。是日采艾为人形，悬于户上，可禳毒气。”"
      },
      content_6: {
        default: "        端午食粽，是中华民族自古以来的传统习俗。粽子，属籺的一种，籺是人们在逢年过节时用来拜神的贡品，逢年过节做籺拜神是古老的传统习俗。籺有很多品种，不同的节日会做不同的籺，粽籺是端午节拜神的贡品。粽的主要材料是稻米、馅料和箬叶（或柊叶）等，其花样繁多。由于各地饮食习惯的不同，粽形成了南北风味；从口味上分，粽子有咸粽和甜粽两大类。端午食粽的风俗，千百年来在中国盛行不衰，已成了中华民族影响最大、覆盖面最广的民间饮食习俗之一，而且流传到朝鲜、日本及东南亚诸国。\n" +
          "        “粽”字本作“糉”，历史上跟粽子沾边的文字记载，最早大概见于汉代许慎的《说文解字》，将之解释为“芦叶裹米也”。西晋周处所写的《风士记》，则明确提到了“角黍”一词：“仲夏端五，方伯协极。享用角黍，龟鳞顺德。”粽最初是用来拜祭祖先和神灵，具体起源年代无考。东汉末年，以草木灰水浸泡黍米，因水中含“碱”，用菰叶包黍米成四角形，煮熟，即为广东碱水粽。到了晋代，正式定粽子为端午节的节庆食品。南北朝时期，出现杂粽，米中掺杂禽兽肉、板栗、红枣、赤豆等，品种增多，粽子还用作交往的礼品。到了唐代，粽子的用米，已“白莹如玉”，其形状出现锥形、菱形。宋朝时，已有“蜜饯粽”，即果品入粽。明清两代，粽子成了吉祥食品；、相传，那时凡参加科举考试的秀才，在赴考场前，要吃家中特意给他们包的“笔粽”，样子细长很像毛笔，谐音“必中”，为的是讨个口彩。"
      },
      content_7: {
        default: "        在中国南方一带，端午节儿童放纸鸢称为“放殃”。纸鸢，在竹篾等骨架上糊上纸或绢，拉着系在上面的长线，趁着风势可以放上天空，属于一种单纯利用空气动力的飞行器。"
      },
      content_8: {
        default: "        草药水，即是古籍记载的沐兰汤，此俗至今尚存，且广泛流行，可治皮肤病、去邪气。关于洗草药水习俗的现存文字记载最早见于西汉末的《大戴礼记》中，但文中的兰不是兰花，而是菊科的佩兰或草药，有香气，可煎水沐浴。民俗认为，端午日午时阳气旺盛，是草木一年中药性最强的一天，端午遍地皆药。端午期间，我国不少地方有采草药煮草药水沐浴的习俗，端午草药的药性在其中发挥了至关重要的作用。《岁时广记》卷二十二“采杂药”引《荆楚岁时记》佚文：“五月五日，竞采杂药，可治百病。”在广东，儿童用苦草麦药或艾、蒲、凤仙、白玉兰等花草煮水洗，少年、成年男子则到江河、海边冲凉，谓之洗龙舟水，洗去晦气，带来好运。在湖南、广西等地，则用柏叶、大风根、艾草、菖蒲、桃叶等煮成药水洗浴，不论男女老幼，全家都洗。"
      },
      content_9: {
        default: "        中国传统文化中，象征五方五行的五种颜色“青、红、白、黑、黄”被视为吉祥色。五彩丝线或源于古代南方人的文身之遗俗，《汉书》：“越人常在水中，故断其发、文其身，以象龙子，故不见伤害也。”端午以五色丝线系臂，曾是很流行的节俗。传到后世，即发展成如长命缕、长命锁、佩香包等许多种漂亮饰物，制作也日趋精致，成为端午节特有的民间艺品。\n" +
          "        在端午节节这天，孩子们要在手腕脚腕上系上五色丝线以驱邪。传统之俗，用红绿黄白黑色粗丝线搓成彩色线绳，系在小孩子的手臂或颈项上，自五月五日系起，一直至七夕“七娘妈”生日，才解下来连同金楮焚烧。 还有一说，在端午节后的第一个雨天，把五彩线剪下来扔在雨中，意味着让河水将瘟疫、疾病冲走，谓之可去邪祟、攘灾异，会带来一年的好运。"
      },
      content_10: {
        default: "        午时水，即是在端午日午时于井里打上来的水。“午”是阳性，古人认为这天的午时阳气最盛，端午日午时驱邪最佳，所以端午当天有打“午时水”的说法，用来煮饭、烧水、煮汤，具有辟邪、净身、除障的效果。据说午时水用来泡茶酿酒特别香醇，生饮甚至具有治病的奇效。有谚语道：“午时洗目，明到若乌鹙”，又说“午时水饮一嘴，较好补药吃三年”。"
      },
      content_11: {
        default: "        东汉王充的《论衡》记载了端午节“铸阳燧”的礼仪习俗：“阳燧取火于天，于五月丙午日中之时，消炼五石，铸以为器，摩励生光，仰以向日，则火来至，此取真火之道也”。古人认为，午月午日午时具三重之火，是阳气极盛之时，在此时刻以火克金，是最佳的熔金铸镜的时刻，铸成的铜镜具有不可思议的神力。以镜辟邪的习俗，在南方沿海一带有着广泛的应用和遗传，常能见到出生不久的小孩子，身上佩带着银制的镜子等饰物，这类银饰小镜子等，就是用于孩子们辟邪。新建的寺庙道观，其屋脊的正中也往往装饰着铜镜。就是现代小区崭新建筑的门、窗上方，也往往能发现悬挂着的镜子，这些都是用于所谓的辟邪。由此可见镜子辟邪的文化信仰在民间根深蒂固。"
      },
      content_12: {
        default: "        广东人将端午前后下的雨水称之为端阳水或龙降水、龙舟水，认为这种水是吉祥的水，有辟邪作用。仲夏端午苍龙群星飞升于正南中天。在民间信俗中龙是吉祥之物、和风化雨的主宰，龙飞天，行云布雨。自然现象上，每年的端午节前后，我国南方暖湿气流活跃，与从北方南下的冷空气在广东和广西、福建、海南交汇，往往会出现持续大范围的强降水。当端午强降水来时，江河水位迅速上涨，为扒龙舟提供了良好的场地条件，这是“龙舟水”得名的原因之一。浸龙舟水是流行于南方沿海一带的传统习俗，因为龙舟水寓意吉祥，龙舟水及龙舟滑过的水人们都认为是“大吉水”，按照传统说法浸龙舟水寓意吉祥如意、事事顺心。"
      },
      content_13: {
        default: "        旧时端五节妇女的头饰，多见于江南。一些地区亦称作健人。此物一说源于古代的步摇，一说即艾人的别样形式。《清嘉录》引《唐宋遗纪》云：“江谁南北，五日钗头彩胜之制，备极奇巧。…加以幡幢宝盖，绣球繁缨，钟铃百状，或贯以串，名曰豆娘，不可胜纪。”"
      },
      content_14: {
        default: "        躲端午，简称“躲午”，亦称“躲端五”，地方民俗，是旧时流传于我国北方的端午节习俗，指接新嫁或已嫁之女回家度节。古时北方俗以五月、五月五日为恶月、恶日，诸事多需避忌，因有接女归家躲端午之俗。《嘉靖隆庆志》亦记云：“已嫁之女召还过节”。又，《滦州志》：“女之新嫁者，于是月俱迎以归，谓之‘躲端午’”。"
      },
      content_15: {
        default: "        端午在古代北方人心目中是毒日、恶日，在民间这个思想一直传了下来，所以才有种种求平安、避五毒的习俗。其实，这是由于北方夏季天气燥热，人易生病，瘟疫也易流行；加上蛇虫繁殖，易咬伤人，这才形成此习惯。民间认为五月是五毒（蝎、蛇、蜈蚣、壁虎、蟾蜍）出没之时，要用各种方法以预防五毒之害。一般在屋中贴五毒图，以红纸印画五种毒物，再用五根针刺于五毒之上，即认为毒物被刺死，再不能横行了。民间又在衣饰上绣制五毒，在饼上缀五毒图案，均含驱除之意。"
      },
      content_16: {
        default: "        采药是最古老的端午节习俗之一。民间习俗认为，端午阳气旺盛，是草木一年中药性最强的一天，端午遍地皆药。采药是因端午前后草药茎叶成熟，药性好，才于此日形成此俗。在端午采药与设置种种可驱邪的花草，其来源久远，这一风俗在汉代时已影响广泛，后来也逐渐影响到东亚各国。《夏小正》载：“此日蓄药，以蠲除毒气。”《岁时广记》卷二十二“采杂药”引《荆楚岁时记》佚文：“五月五日，竞采杂药，可治百病。”后魏《齐民要术·杂记》中有五月捉蛤蟆的记载，亦是制药用。中国民间至今仍普遍保留着端午所采之药最为灵验的信仰。支撑这类俗信的原理，自然也是基于一种宇宙论式的解说：端午或这天午时，由于季节变动致使阳气极盛，但同时也是各种草药的生长最为茂盛之时，所以，这天采的草药最为灵验、有效。"
      },
      content_17: {
        default: "        饮蒲酒、雄黄、朱砂酒，以酒洒喷。《荆楚岁时记》：“以菖蒲（多年生草本植物，生在水边，地下有淡红色根茎，叶子形状像剑，肉穗花序。根茎可做香料，也可入药）或镂或屑，以冷酒。”蒲酒味芳香，有爽口之感，后来又在酒中加入雄黄、朱砂等。明冯应京《月令广义》：“五日用朱砂酒，辟邪解毒，用酒染额胸手足心，无会虺（古书上说的一种毒蛇）蛇之患。又以洒墙壁门窗，以避毒虫。”此俗流传较广。\n" +
          "        药料包括雄黄、朱耒、柏子、桃仁、蒲片、艾叶等，人们浸入酒后再用菖蒲艾蓬蘸洒墙壁角落、门窗、床下等，再用酒涂小儿耳鼻、肚脐，以驱毒虫，求小儿平安。另外有的地区还用雄黄酒末在小孩额上画“王”字，使小孩带有虎的印记，以用虎辟邪。这些活动，从卫生角度来看，还是有科学道理的。雄黄加水和酒洒于室内可消毒杀菌，饮蒲洒也颇有益。"
      },
      content_18: {
        default: "        端午下雨南方称之为龙舟水、端阳水，洗龙舟水能去晦气、带来吉祥。北方认为端阳无雨是丰年，端午日雨，鬼旺人灾；五月五日哨，人曝药，岁无灾；雨则鬼曝药，人多病；此种俗信在很久以前即已存在；陈元靓《岁时广记》引《提要录》云：“五月五日哨，人曝药，岁无灾。雨则鬼曝药，人多病。”又许月卿《次韵蜀人李施州芾端午》自注云：“临川人谓端午日雨，鬼旺人灾；清赵怀玉诗自注亦引有“端阳无雨是丰年”的谚语。"
      },
      content_19: {
        default: "        跳钟馗、闹钟馗：自晋代以来跳钟馗、闹钟馗，赐福镇宅就是端午节和春节的重要内容。悬挂钟馗画：最早的钟馗画，据说出自画圣吴道子之手。\n" +
          "        据北宋沈括《梦溪补笔谈》记载：“唐明皇患疾近一月，忽梦二鬼。小鬼窃得皇上玉笛及杨贵妃之紫香囊奔逃，大鬼乃捉小鬼刳其目，然后擘而啖之。……唐明皇醒后，即病愈，便召画工吴道子，嘱其按梦中情景画钟馗捉鬼图，道子挥笔即成。”后来，钟馗画渐渐走入民间，绘制钟馗画大都作于五月端午节这一天，或者端午节的前后几天。到了清代，江南苏、浙一带的居民大都在农历五月于大门或堂中挂钟馗图一月，以期驱邪除害、祛凶引福。"
      },
      content_20: {
        default: "        汉以前不见斗草之戏（《历代社会通俗事物考·尚秉和》）。起源无考，普遍认为与中医药学的产生有关。远古先民暇余以斗虫、斗草、斗兽等为戏自娱。收获之余，往往举行比赛，以对仗形式互报花名、草名，多者为赢，兼具植物知识、文学知识之妙趣；儿童则以叶柄相勾，捏住相拽，断者为输，再换一叶相斗。白居易《观儿戏》诗云：“弄尘或斗草，尽日乐嬉嬉。”\n" +
          "        《物原》云：“始于汉武”。据梁朝人宗懔在《荆楚岁时记》中云：“五月五日，四民并踏百草，又有斗草之戏。”《年华记丽》：“端午结庐蓄药，斗百草，缠五丝。”\n" +
          "        南北朝时称“踏百草”，唐代称“斗草”或“斗百草”。《刘宾客嘉话》云：“唐中宗朝，安乐公主五日斗百草。”宋代扩展至平日随时可斗。历代文人作品中对此多有描述。"
      },
      content_21: {
        default: "        马球，是骑在马上，持棍打球，古称击鞠。中国北方民族没有端午龙舟竞渡的习俗，但会在端午这天射柳和打马球，这很明显是来源于北方游牧民族的竞技遗俗。马球，是骑在马上，持棍打球，古称击鞠。"
      },
      content_22: {
        default: "        近代，在江西省赣州市上犹县，当地独有的客家民俗活动“九狮拜象”表演团在该县欢乐巡游。“九狮拜象”在传统的舞龙灯基础上，凸显舞狮象造型。“九狮拜象”距今已有300多年历史，既有传统文化的传承，又有客家人的文化创新，表达了对风调雨顺年成丰熟的祈愿和对安定幸福生活的向往。"
      },
      content_23: {
        default: "        粽籺：端午节食粽是我国的传统习俗，粽的形状很多，品种各异，一般还有正三角形、正四角形、尖三角形、方形、长形等各种形状。由于中国各地风味不同，主要有甜、咸两种。"
      },
      content_24: {
        default: "        雄黄酒：端午饮雄黄酒的习俗，从前在长江流域地区极为盛行。用研磨成粉末的雄黄泡制的白酒或黄酒。雄黄可以用做解毒剂、杀虫药。于是古代人就认为雄黄可以克制蛇、蝎等百虫，“善能杀百毒、辟百邪、制蛊毒，人佩之，入山林而虎狼伏，入川水而百毒避”。"
      },
      content_25: {
        default: "        五黄：江浙一带有端午节吃“五黄”的习俗。五黄，是指黄鱼、黄瓜、黄鳝、鸭蛋黄、雄黄酒（雄黄酒有毒性，一般都喝普通的黄酒代替雄黄酒）。也有其它说法，咸鸭蛋可以用黄豆替代。农历五月，江南人称五黄月。"
      },
      content_26: {
        default: "        打糕：端午节是吉林省延边朝鲜族人民隆重的节日。这一天最有代表性的食品是清香的打糕。打糕，就是将艾蒿与糯米饭，放置于独木凿成的大木槽里，用长柄木捶打制而成的米糕。这种食品很有民族特色，又可增添节日的气氛。"
      },
      content_27: {
        default: "        煎堆：福建晋江地区，端午节家家户户还要吃“煎堆”，就是用面粉、米粉或番薯粉和其他配料调成浓糊状煎成。相传古时闽南一带在端午节之前是雨季，阴雨连绵不止，民间说天公穿了洞，要“补天”。端午节吃了“煎堆”后雨便止了，人们说把天补好了。这种食俗由此而来。"
      },
    },
    methods: {
      font: function (size) {
        return util.getFontSize(size)
      }
    },
  }
</script>

<style scoped>

</style>
