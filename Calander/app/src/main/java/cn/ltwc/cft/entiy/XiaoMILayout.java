package cn.ltwc.cft.entiy;

import java.util.List;

public class XiaoMILayout {


    /**
     * channel : 03-1
     * tn : Z
     * layoutId : tq_v8_1
     * dataVersion : 01_
     * data : [{"title":"出行建议","summary":"","category":"","template":1,"image":"","id":105,"module":[],"perpage":0,"cornerTip":"","rowType":"card","statKey":"105","visible":true,"list":[{"type":"002","template":0,"data":{"id":"","indexType":"用伞指数","statKey":"用伞指数","image":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/e4d86fce049ffe7a01c550664af81d616affae72.png","summary":"今日出行无需携带雨具哟！","url":"","views":"","dataId":"002-020-1462855888870","newsId":"","imageNum":1,"title":"不用带伞","newsDate":1487835123000,"images":["https://sec-cdn.static.xiaomi.net/secStatic/imgs/e4d86fce049ffe7a01c550664af81d616affae72.png"],"category":"","source":"MIUI天气","channelId":"03-6","headPic":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/92c5b28a55c98bca22995f124552933352a5d596.png","details":[],"module":[{"action":"","actionIcon":"","icon":"","id":0,"packageClass":"","packageName":"","purl":"","subTitle":"","title":"","url":"","weight":""}],"template":1,"dv":0},"rowType":"item_show_img"},{"type":"002","template":0,"data":{"id":"","indexType":"穿衣指数","statKey":"穿衣指数","image":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/d3a311e3c1a2808ca64c8e1af6f8fdf3a828a458.png","summary":"注意下肢保暖，年老体弱者尽量减少外出哦！","url":"","views":"","dataId":"002-020-1462855718034","newsId":"","imageNum":1,"title":"适宜厚羽绒服","newsDate":1487835123000,"images":["https://sec-cdn.static.xiaomi.net/secStatic/imgs/d3a311e3c1a2808ca64c8e1af6f8fdf3a828a458.png"],"category":"","source":"MIUI天气","channelId":"03-2","headPic":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/7eb536c5c8e26c5de6301587614aa5726ac1ca26.png","details":[],"module":[{"action":"","actionIcon":"","icon":"","id":0,"packageClass":"","packageName":"","purl":"","subTitle":"","title":"","url":"","weight":""}],"template":1,"dv":0},"rowType":"item_show_img"},{"type":"002","template":0,"data":{"id":"","indexType":"紫外线指数","statKey":"紫外线指数","image":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/16dc6d3db901f425e30e53658bf47b072d9b7226.png","summary":"大胆迈开腿吧，防晒霜、遮阳帽统统丢掉。","url":"","views":"","dataId":"002-020-1462855932095","newsId":"","imageNum":1,"title":"无需防晒","newsDate":1487835123000,"images":["https://sec-cdn.static.xiaomi.net/secStatic/imgs/16dc6d3db901f425e30e53658bf47b072d9b7226.png"],"category":"","source":"MIUI天气","channelId":"03-3","headPic":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/e37bfdcaf028c6532f04fb83cf657a972f33f263.png","details":[],"module":[{"action":"","actionIcon":"","icon":"","id":0,"packageClass":"","packageName":"","purl":"","subTitle":"","title":"","url":"","weight":""}],"template":1,"dv":0},"rowType":"item_show_img"}]},{"title":"运动建议","summary":"","category":"","template":1,"image":"","id":104,"module":[],"perpage":0,"cornerTip":"","rowType":"card","statKey":"104","visible":true,"list":[{"type":"002","template":0,"data":{"id":"","indexType":"运动指数","statKey":"运动指数","image":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/c6b020c6164a59caec83eaa14b7f5b6dd234e819.png","summary":"空气质量较差，外出活动需做好防护措施","url":"","views":"","dataId":"002-020-1462855907701","newsId":"","imageNum":1,"title":"适宜室内运动","newsDate":1487835123000,"images":["https://sec-cdn.static.xiaomi.net/secStatic/imgs/c6b020c6164a59caec83eaa14b7f5b6dd234e819.png"],"category":"","source":"MIUI天气","channelId":"03-4","headPic":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/89c9c5750936466b8ae844e938aa988c0b797e2e.png","details":[],"module":[{"action":"","actionIcon":"","icon":"","id":0,"packageClass":"","packageName":"","purl":"","subTitle":"","title":"","url":"","weight":""}],"template":1,"dv":0},"rowType":"item_show_img"}]},{"title":"驾车建议","summary":"","category":"","template":1,"image":"","id":106,"module":[],"perpage":0,"cornerTip":"","rowType":"card","statKey":"106","visible":true,"list":[{"type":"002","template":0,"data":{"id":"","indexType":"洗车指数","statKey":"洗车指数","image":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/030bbbf7c47e0b12ecd39b25c1da39ca2ecc49d8.png","summary":"今日适宜洗车，快给爱车冲凉吧！","url":"","views":"","dataId":"002-020-1462855950385","newsId":"","imageNum":1,"title":"适宜洗车","newsDate":1487835123000,"images":["https://sec-cdn.static.xiaomi.net/secStatic/imgs/030bbbf7c47e0b12ecd39b25c1da39ca2ecc49d8.png"],"category":"","source":"MIUI天气","channelId":"03-5","headPic":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/6fdab1422a2267766de3a88d13ac96994d3aabc8.png","details":[],"module":[{"action":"","actionIcon":"","icon":"","id":0,"packageClass":"","packageName":"","purl":"","subTitle":"","title":"","url":"","weight":""}],"template":1,"dv":0},"rowType":"item_show_img"}]}]
     */

    private String channel;
    private String tn;
    private String layoutId;
    private String dataVersion;
    private List<DataBeanW> data;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(String layoutId) {
        this.layoutId = layoutId;
    }

    public String getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion;
    }

    public List<DataBeanW> getData() {
        return data;
    }

    public void setData(List<DataBeanW> data) {
        this.data = data;
    }

    public static class DataBeanW {
        /**
         * title : 出行建议
         * summary :
         * category :
         * template : 1
         * image :
         * id : 105
         * module : []
         * perpage : 0
         * cornerTip :
         * rowType : card
         * statKey : 105
         * visible : true
         * list : [{"type":"002","template":0,"data":{"id":"","indexType":"用伞指数","statKey":"用伞指数","image":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/e4d86fce049ffe7a01c550664af81d616affae72.png","summary":"今日出行无需携带雨具哟！","url":"","views":"","dataId":"002-020-1462855888870","newsId":"","imageNum":1,"title":"不用带伞","newsDate":1487835123000,"images":["https://sec-cdn.static.xiaomi.net/secStatic/imgs/e4d86fce049ffe7a01c550664af81d616affae72.png"],"category":"","source":"MIUI天气","channelId":"03-6","headPic":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/92c5b28a55c98bca22995f124552933352a5d596.png","details":[],"module":[{"action":"","actionIcon":"","icon":"","id":0,"packageClass":"","packageName":"","purl":"","subTitle":"","title":"","url":"","weight":""}],"template":1,"dv":0},"rowType":"item_show_img"},{"type":"002","template":0,"data":{"id":"","indexType":"穿衣指数","statKey":"穿衣指数","image":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/d3a311e3c1a2808ca64c8e1af6f8fdf3a828a458.png","summary":"注意下肢保暖，年老体弱者尽量减少外出哦！","url":"","views":"","dataId":"002-020-1462855718034","newsId":"","imageNum":1,"title":"适宜厚羽绒服","newsDate":1487835123000,"images":["https://sec-cdn.static.xiaomi.net/secStatic/imgs/d3a311e3c1a2808ca64c8e1af6f8fdf3a828a458.png"],"category":"","source":"MIUI天气","channelId":"03-2","headPic":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/7eb536c5c8e26c5de6301587614aa5726ac1ca26.png","details":[],"module":[{"action":"","actionIcon":"","icon":"","id":0,"packageClass":"","packageName":"","purl":"","subTitle":"","title":"","url":"","weight":""}],"template":1,"dv":0},"rowType":"item_show_img"},{"type":"002","template":0,"data":{"id":"","indexType":"紫外线指数","statKey":"紫外线指数","image":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/16dc6d3db901f425e30e53658bf47b072d9b7226.png","summary":"大胆迈开腿吧，防晒霜、遮阳帽统统丢掉。","url":"","views":"","dataId":"002-020-1462855932095","newsId":"","imageNum":1,"title":"无需防晒","newsDate":1487835123000,"images":["https://sec-cdn.static.xiaomi.net/secStatic/imgs/16dc6d3db901f425e30e53658bf47b072d9b7226.png"],"category":"","source":"MIUI天气","channelId":"03-3","headPic":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/e37bfdcaf028c6532f04fb83cf657a972f33f263.png","details":[],"module":[{"action":"","actionIcon":"","icon":"","id":0,"packageClass":"","packageName":"","purl":"","subTitle":"","title":"","url":"","weight":""}],"template":1,"dv":0},"rowType":"item_show_img"}]
         */

        private String title;
        private String summary;
        private String category;
        private int template;
        private String image;
        private int id;
        private int perpage;
        private String cornerTip;
        private String rowType;
        private String statKey;
        private boolean visible;
        private List<?> module;
        private List<ListBean> list;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getTemplate() {
            return template;
        }

        public void setTemplate(int template) {
            this.template = template;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPerpage() {
            return perpage;
        }

        public void setPerpage(int perpage) {
            this.perpage = perpage;
        }

        public String getCornerTip() {
            return cornerTip;
        }

        public void setCornerTip(String cornerTip) {
            this.cornerTip = cornerTip;
        }

        public String getRowType() {
            return rowType;
        }

        public void setRowType(String rowType) {
            this.rowType = rowType;
        }

        public String getStatKey() {
            return statKey;
        }

        public void setStatKey(String statKey) {
            this.statKey = statKey;
        }

        public boolean isVisible() {
            return visible;
        }

        public void setVisible(boolean visible) {
            this.visible = visible;
        }

        public List<?> getModule() {
            return module;
        }

        public void setModule(List<?> module) {
            this.module = module;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * type : 002
             * template : 0
             * data : {"id":"","indexType":"用伞指数","statKey":"用伞指数","image":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/e4d86fce049ffe7a01c550664af81d616affae72.png","summary":"今日出行无需携带雨具哟！","url":"","views":"","dataId":"002-020-1462855888870","newsId":"","imageNum":1,"title":"不用带伞","newsDate":1487835123000,"images":["https://sec-cdn.static.xiaomi.net/secStatic/imgs/e4d86fce049ffe7a01c550664af81d616affae72.png"],"category":"","source":"MIUI天气","channelId":"03-6","headPic":"https://sec-cdn.static.xiaomi.net/secStatic/imgs/92c5b28a55c98bca22995f124552933352a5d596.png","details":[],"module":[{"action":"","actionIcon":"","icon":"","id":0,"packageClass":"","packageName":"","purl":"","subTitle":"","title":"","url":"","weight":""}],"template":1,"dv":0}
             * rowType : item_show_img
             */

            private String type;
            private int template;
            private DataBean data;
            private String rowType;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getTemplate() {
                return template;
            }

            public void setTemplate(int template) {
                this.template = template;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }

            public String getRowType() {
                return rowType;
            }

            public void setRowType(String rowType) {
                this.rowType = rowType;
            }

            public static class DataBean {
                /**
                 * id :
                 * indexType : 用伞指数
                 * statKey : 用伞指数
                 * image : https://sec-cdn.static.xiaomi.net/secStatic/imgs/e4d86fce049ffe7a01c550664af81d616affae72.png
                 * summary : 今日出行无需携带雨具哟！
                 * url :
                 * views :
                 * dataId : 002-020-1462855888870
                 * newsId :
                 * imageNum : 1
                 * title : 不用带伞
                 * newsDate : 1487835123000
                 * images : ["https://sec-cdn.static.xiaomi.net/secStatic/imgs/e4d86fce049ffe7a01c550664af81d616affae72.png"]
                 * category :
                 * source : MIUI天气
                 * channelId : 03-6
                 * headPic : https://sec-cdn.static.xiaomi.net/secStatic/imgs/92c5b28a55c98bca22995f124552933352a5d596.png
                 * details : []
                 * module : [{"action":"","actionIcon":"","icon":"","id":0,"packageClass":"","packageName":"","purl":"","subTitle":"","title":"","url":"","weight":""}]
                 * template : 1
                 * dv : 0
                 */

                private String id;
                private String indexType;
                private String statKey;
                private String image;
                private String summary;
                private String url;
                private String views;
                private String dataId;
                private String newsId;
                private int imageNum;
                private String title;
                private long newsDate;
                private String category;
                private String source;
                private String channelId;
                private String headPic;
                private int template;
                private int dv;
                private List<String> images;
                private List<?> details;
                private List<ModuleBean> module;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getIndexType() {
                    return indexType;
                }

                public void setIndexType(String indexType) {
                    this.indexType = indexType;
                }

                public String getStatKey() {
                    return statKey;
                }

                public void setStatKey(String statKey) {
                    this.statKey = statKey;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getSummary() {
                    return summary;
                }

                public void setSummary(String summary) {
                    this.summary = summary;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public String getViews() {
                    return views;
                }

                public void setViews(String views) {
                    this.views = views;
                }

                public String getDataId() {
                    return dataId;
                }

                public void setDataId(String dataId) {
                    this.dataId = dataId;
                }

                public String getNewsId() {
                    return newsId;
                }

                public void setNewsId(String newsId) {
                    this.newsId = newsId;
                }

                public int getImageNum() {
                    return imageNum;
                }

                public void setImageNum(int imageNum) {
                    this.imageNum = imageNum;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public long getNewsDate() {
                    return newsDate;
                }

                public void setNewsDate(long newsDate) {
                    this.newsDate = newsDate;
                }

                public String getCategory() {
                    return category;
                }

                public void setCategory(String category) {
                    this.category = category;
                }

                public String getSource() {
                    return source;
                }

                public void setSource(String source) {
                    this.source = source;
                }

                public String getChannelId() {
                    return channelId;
                }

                public void setChannelId(String channelId) {
                    this.channelId = channelId;
                }

                public String getHeadPic() {
                    return headPic;
                }

                public void setHeadPic(String headPic) {
                    this.headPic = headPic;
                }

                public int getTemplate() {
                    return template;
                }

                public void setTemplate(int template) {
                    this.template = template;
                }

                public int getDv() {
                    return dv;
                }

                public void setDv(int dv) {
                    this.dv = dv;
                }

                public List<String> getImages() {
                    return images;
                }

                public void setImages(List<String> images) {
                    this.images = images;
                }

                public List<?> getDetails() {
                    return details;
                }

                public void setDetails(List<?> details) {
                    this.details = details;
                }

                public List<ModuleBean> getModule() {
                    return module;
                }

                public void setModule(List<ModuleBean> module) {
                    this.module = module;
                }

                public static class ModuleBean {
                    /**
                     * action :
                     * actionIcon :
                     * icon :
                     * id : 0
                     * packageClass :
                     * packageName :
                     * purl :
                     * subTitle :
                     * title :
                     * url :
                     * weight :
                     */

                    private String action;
                    private String actionIcon;
                    private String icon;
                    private int id;
                    private String packageClass;
                    private String packageName;
                    private String purl;
                    private String subTitle;
                    private String title;
                    private String url;
                    private String weight;

                    public String getAction() {
                        return action;
                    }

                    public void setAction(String action) {
                        this.action = action;
                    }

                    public String getActionIcon() {
                        return actionIcon;
                    }

                    public void setActionIcon(String actionIcon) {
                        this.actionIcon = actionIcon;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getPackageClass() {
                        return packageClass;
                    }

                    public void setPackageClass(String packageClass) {
                        this.packageClass = packageClass;
                    }

                    public String getPackageName() {
                        return packageName;
                    }

                    public void setPackageName(String packageName) {
                        this.packageName = packageName;
                    }

                    public String getPurl() {
                        return purl;
                    }

                    public void setPurl(String purl) {
                        this.purl = purl;
                    }

                    public String getSubTitle() {
                        return subTitle;
                    }

                    public void setSubTitle(String subTitle) {
                        this.subTitle = subTitle;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public String getWeight() {
                        return weight;
                    }

                    public void setWeight(String weight) {
                        this.weight = weight;
                    }
                }
            }
        }
    }
}
