<template>
    <scroller>
        <div>
            <image :style="{ width: img_w_top, height:img_1_h }"
                   src="http://zerosboy.site/Ahuangshang/img/dongzhi/dongzhi_1.jpg"
                   placeholder=""></image>
            <div :style="{ marginLeft: font(contentMargin),marginRight: font(contentMargin)}">
                <text :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
                      style="color: #334f16;" :value=title>
                </text>
                <text :style="{ fontSize: font(tex_size_2) ,marginTop:font(contentMargin)}"> 起源</text>
                <!--<text style=" font-size: 35px;margin-top: 15px"> 妇女乞巧 </text>-->
                <text :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
                      style="color: #334f16;" :value=content_1>
                </text>
                <image :style="{ width: img_w, height:img_3_h }"
                       src="http://zerosboy.site/Ahuangshang/img/dongzhi/dongzhi_3.jpg"></image>
                <text :style="{ fontSize: font(tex_size_2)}" style="margin-top: 15px"> 天文意义</text>
                <text :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
                      style="color: #334f16;" :value=content_2>
                </text>
                <image :style="{ width: img_w, height:img_2_h }"
                       src="http://zerosboy.site/Ahuangshang/img/dongzhi/dongzhi_2.jpg"
                       placeholder=""></image>
                <text :style="{ fontSize: font(tex_size_2)}" style="margin-top: 15px"> 历史渊源</text>
                <text :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
                      style="color: #334f16;" :value=content_3>
                </text>
                <image :style="{ width: img_w, height:img_4_h }"
                       src="http://zerosboy.site/Ahuangshang/img/dongzhi/dongzhi_4.jpg"
                       placeholder=""></image>
                <text :style="{ fontSize: font(tex_size_2)}" style="margin-top: 15px"> 民俗活动</text>
                <text :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
                      style="color: #334f16;" :value=content_4>
                </text>
                <image :style="{ width: img_w, height:img_5_h }"
                       src="http://zerosboy.site/Ahuangshang/img/dongzhi/dongzhi_5.jpg"
                       placeholder=""></image>
                <text :style="{ fontSize: font(tex_size_2)}" style="margin-top: 15px">传统饮食</text>
                <text :style="{ fontSize: font(tex_size_1),marginTop: font(topMargin), margin:font(contentMargin),lineHeight:font(line_height)}"
                      style="color: #334f16;" :value=content_5>
                </text>
            </div>
        </div>
    </scroller>

</template>

<script>
    //  var buiweex = require("bui-weex");
    const modal = weex.requireModule('modal');
    const util = require('../js/methods.js');
    export default {
        props: {
            img_w_top: {
                default: 750
            },
          img_w: {
            default: 718
          },
            img_1_h: {
                default: 468.75
            },
            img_2_h: {
                default: 432.56
            },

            img_3_h: {
                default: 538.50
            },

            img_4_h: {
                default: 563.43
            },
            img_5_h: {
                default: 513.02
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
                default: "       冬至是农历的重要节气，也是中华民族的传统节日。早在春秋时代，中国就已经用土圭观测出冬至，古人认为自冬至起阳气回升，代表着下一个循环的开始。冬至是重要的养生时期，应注意防寒保暖、保肝护肝。"
            },
            content_1: {
                default: "       冬至这天，太阳运行至黄经270°（冬至点），太阳直射地面的位置到达一年的最南端，太阳几乎直射南回归线（又称为冬至线），阳光对北半球最为倾斜。因此，冬至日是北半球各地一年中白昼最短的一天，并且越往北白昼越短。在北极圈以北，这一天太阳整日都在地平线之下，成为北半球一年中极夜范围最广的一天。对北半球各地而言，冬至也是全年正午太阳高度最低的一天。就北京市区来说，冬至这天白昼仅有9小时20分，而正午太阳高度也仅有26°42'。冬至这天，北半球得到的太阳辐射最少，比南半球少了约50%。冬至节届即一阳生，新岁实始。《载敬堂集》载“夏尽秋分日，春生冬至时”，冬至节，春之先声也。冬至过后，夜空星象则完全换成冬季星空，并且从今天起开始“进九”。而此时的南半球正值酷热的盛夏。\n" +
                "冬至是24节气中最早被制订的一个，然而多数人并不知道，冬至的起源居然是来自于一次国家层面的都城规划。早在3000多年前，周公始用土圭法测影，在洛邑测得天下之中的位置，定此为土中，这在当时有着政治意义的举动，却成了影响后世几千年的节日之一。\n" +
                "       周公到洛阳，用土圭法测得洛阳所处的地方即为“天下之中”，然后开始占卜国家社稷的吉地。《尚书·洛诰》记载：周公“朝至于洛师”，对洛阳周边的几个地方做了考察，最后确定涧水东、瀍水西、瀍水东皆“惟洛食”（都是兴建宗庙社稷的好地方）。周公通过“土圭测景”选定洛邑基址的史实，被载入了古代典籍，也被后人奉为封邦建国的成法。\n" +
                "       “土圭测景”的目的是找出“土中”（中国的中心）。这种方法的要义是“树八尺之表，夏至日，景长尺有五寸；冬至日，景长一丈三尺五寸”（即竖起高为8尺的标杆，在夏至日观测，中午的日影是1.5尺，冬至日中午的日影是13.5尺）， “测土深，正日影，求地中，验四时”。 用这种方法测到的就是“土中”洛阳、“洛邑”的理论位置。\n" +
                "       依周公测影所定的天下之中，周人详细规划了灭商后的第一座国家都城，《逸周书·作雒》载：“乃作大邑成周于土中，……南系于洛水，北因于邙山，以为天下之大凑。” 。“定天保，依天室”，国家社稷（都城、宗庙）完成之后，周公在成周明堂制礼作乐，详细制订了国家礼仪制度， 据记载，周代以冬十一月为正月，以冬至为岁首过新年，也就是说，周公选取的是经土圭法测得的一年中“日影”最长的一天，为新的一年开始的日子。\n" +
                "       由周到秦，以冬至日当作岁首一直不变。至汉代依然如此，《汉书》有云：“冬至阳气起，君道长，故贺……”也就是说，人们最初过冬至节是为了庆祝新的一年的到来。古人认为自冬至起，天地阳气开始兴作渐强，代表下一个循环开始，是大吉之日。因此，后来一般春节期间的祭祖、家庭聚餐等习俗，也往往出现在冬至。冬至又被称为“小年”，一是说明年关将近，余日不多；二是表示冬至的重要性。把冬至作为节日来过源于汉代，盛于唐宋，并相沿至今。周历的正月为夏历的十一月，因此，周代的正月等于如今的十一月，所以拜岁和贺冬并没有分别。直到汉武帝采用夏历后，才把正月和冬至分开。因此，也可以说专门过“冬至节”是自汉代以后才有，盛于唐宋，相沿至今。"
            },
            content_2: {
                default: "       现代天文科学测定，冬至这天，太阳运行至黄经270°（冬至点），太阳直射地面的位置到达一年的最南端，太阳几乎直射南回归线（又称为冬至线），阳光对北半球最为倾斜。因此，冬至日是北半球各地一年中白昼最短，黑夜最长的一日，并且越往北白昼越短，黑夜越长。在北极圈以北，这一天太阳整日都在地平线之下，成为北半球一年中极夜范围最广的一天。对北半球各地而言，冬至也是全年正午太阳高度最低的一日。就北京市区来说，冬至这天白昼仅有9小时20分，而正午太阳高度也仅有26°42'。冬至这天，北半球得到的太阳辐射最少，比南半球少了约50%。冬至过后，夜空星象则完全换成冬季星空，并且从今天起开始“进九”。而此时的南半球正值酷热的盛夏。\n" +
                "       值得注意的是，在冬至前后，地球位于近日点附近，运行的速度稍快，这造成了在一年中太阳在南半球的时间比在北半球约短8天，因此北半球的冬季比夏季要略微短一些。\n" +
                "       注：地极轴通过地心，连结南、北两极，与地球公转轨道面的夹角为66°34′，和地球自转轨道面——赤道面垂直"
            },
            content_3: {
                default: "        据记载，周秦时代以冬十一月为正月，以冬至为岁首过新年。《汉书》有云：“冬至阳气起，君道长，故贺……”也就是说，人们开始过冬至节是为了庆祝新的一年的到来。古人认为自冬至开始，天地阳气开始兴作渐强，代表下一个循环开始，大吉之日。所以，后来一般春节期间的祭祖、家庭聚餐等习俗，往往选在在冬至。冬至又被称做“小年”，一是说明年关将近，余日不多；二是表示冬至的重要性。\n" +
                "       把冬至作为节日来过源于汉代，盛于唐宋，相沿至今。周历正月为夏历的十一月，因此，周代正月等于如今公历的十一月，所以拜岁和贺冬并没有分别。直到汉朝汉武帝采用夏历后，把正月和冬至分开。也可以说单纯的过“冬至节”是自汉代以后才有，盛于唐宋，相沿至今。\n" +
                "       汉代以冬至为“冬节”，官府要举行祝贺仪式称为“贺冬”，官方例行放假，官场流行互贺的“拜冬”礼俗。《后汉书》中有这样的记载：“冬至前后，君子安身静体，百官绝事，不听政，择吉辰而后省事。”所以这天朝廷上下要放假休息，军队待命，边塞闭关，商旅停业，亲朋各以美食相赠，相互拜访，欢乐地过一个“安身静体”的节日。魏晋六朝时，冬至称为“亚岁”，民众要向父母长辈拜节；宋朝以后，冬至逐渐成为祭祀祖先和神灵的节庆活动。\n" +
                "       唐、宋时期，冬至是祭天祀祖的日子，皇帝在这天要到郊外举行祭天大典，百姓在这一天要向父母尊长祭拜。明、清两代，皇帝均有祭天大典，谓之“冬至郊天”。宫内有百官向皇帝呈递贺表的仪式，而且还要互相投刺祝贺，就像元旦一样。 而今只有潮汕、浙江部分地区仍延续祭祖旧习。"
            },
            content_4: {
                default: "        冬至节亦称冬节、交冬。它既是二十四节气之一，是中国的一个传统节日，曾有“冬至大如年”的说法，宫廷和民间历来十分重视，从周代起就有祭祀活动。\n" +
                "       《周礼春官·神仕》：“以冬日至，致天神人鬼。”目的在于祈求与消除国中的疫疾，减少荒年与人民的饥饿与死亡。《史记·孝武本纪》：“其后二岁，十一月甲子朔旦冬至，推历者以本统。天子亲至泰山，以十一月甲子朔旦冬至日祠上帝明堂，每修封禅。”\n" +
                "       《后汉书礼仪》：“冬至前后，君子安身静体，百官绝事。”还要挑选“能之士”，鼓瑟吹笙，奏“黄钟之律”，以示庆贺。\n" +
                "       唐宋时，以冬至和岁首并重。南宋孟元老《东京梦华录》：“十一月冬至。京师最重此节，虽至贫者，一年之间，积累假借，至此日更易新衣，备办饮食，享祀先祖。官放关扑，庆祝往来，一如年节。”\n" +
                "       《清嘉录》则直言：“冬至大如年”，汉人自古冬至需拜天祭祖，国内部分地区一直延续着此习俗。"
            },


            content_5: {
                default: "        冬至是养生的大好时机，主要是因为“气始于冬至”。因为从冬季开始，生命活动开始由衰转盛，由静转动。此时科学养生有助于保证旺盛的精力而防早衰，达到延年益寿的目的。冬至时节饮食宜多样，谷、果、肉、蔬合理搭配，适当选用高钙食品。\n" +
                "       各地在冬至时有不同的风俗，中国北方多数地方有冬至吃饺子的习俗。冬至经过数千年发展，形成了独特的节令食文化。吃饺子成为多数中国人冬至的风俗。当然也有例外如在山东省滕州市流行冬至当天喝羊肉汤的习俗，寓意驱除寒冷之意。\n" +
                "       古人喜贺冬至，今人虽多不以为节，但冬节再怎么说也是“年时八节”之一，吃货们还是不会放过这有着各种冬至特色美食的节日的：如北方水饺、潮汕汤圆、东南麻糍、台州擂圆、合肥南瓜饼、宁波番薯汤果、滕州羊肉汤、江南米饭、苏州酿酒等。\n" +
                "北方普遍吃水饺\n" +
                "       每年农历冬至这天，不论贫富，饺子是必不可少的节日饭。谚云：“十月一，冬至到，家家户户吃水饺。”这种习俗，是因纪念“医圣”张仲景冬至舍药留下的。\n" +
                "合肥南瓜饼\n" +
                "       冬至过了眼看年，合肥人到了冬至都要吃南瓜饼，大街小巷弥漫着南瓜饼的香味，并且还有一句谚语叫做“吃了冬至面，一天长一线”，就是说过了冬至，就会夜短日长了。\n" +
                "1.准备好所有食材。把糯米粉和粘米粉混合拌匀。\n" +
                "2.把南瓜削皮去籽切成薄片放在碗中。\n" +
                "3.盖上盖子，把南瓜碗放入微波炉，分次转8分钟左右，直至南瓜软烂。\n" +
                "4.南瓜碗从微波炉中拿出，趁热加入白砂糖，搅拌均匀。\n" +
                "5.用汤匙把南瓜压成南瓜泥。\n" +
                "6.把南瓜泥倒入糯米和粘米粉盆中。\n" +
                "7.用筷子把南瓜和粉搅拌成雪花状，如太干可以稍微加入一点牛奶或者清水。\n" +
                "8.用手把面糊揉成光滑的面团，然后搓成长条，分成大小相当的坯子。\n" +
                "9.把坯子放在手中压扁。\n" +
                "10.放入适量的红豆沙。\n" +
                "11.把放有红豆沙的面团收口，轻轻地在砧板上按成饼状。\n" +
                "12.把南瓜饼两面沾上面包糠。\n" +
                "13.锅中倒入适量的植物油，放入南瓜饼，用小火煎至两面金黄色即可。\n" +
                "       出锅后的南瓜饼放在厨房餐巾纸上吸走多余的油即可装盘食用。"
            },
        },
        methods: {
            font: function (size) {
                return util.getFontSize(size)
            }
        },
    };
</script>