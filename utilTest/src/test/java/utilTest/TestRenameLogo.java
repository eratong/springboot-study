package utilTest;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRenameLogo {

    @Test
    public void s(){
        String s="3b9aba02244ace41,9b8b43ea7ef28514,ccedab478e9ab8d8,4ec861a0f20c3cc9,c83d74243efe40c9,935ba47cd247fd88,315dc70dfd13286a,b1754f3b4fc9ab13,e641bd14ccd6402f,04128034b038995b,a818be60c8b75849,dec9165fe2d97a75,503df0bc7c298718,f76cfd3787eebe64,fad6b9c939e17a92,d8182e733d4d3734,3182f44d78916c70,16770a76289a5d1f,af8d7e982f2cb8c5,f476bd0f4ec19676,4f1c7d5a94355796,20f9b61f993bf026,797c79ff2097ad10,ee56c5f14f2ed0f9,9e83b96ffa821432,0a7a0362b4b6a4b9,90018f904d373cfc,9d73d8e24a8bb553,efc42b2767ddcd41,5a768cd151f9eff7,540db886503c1cee,3a9c4496326598b4,6e926723cfa51b66,c62ae57415a737c6,8889a25df0a33ec7,91ef5fa22246d978,e49cda2b741e16f2,1b7bac13c029a24b,383b2c5bad40e49b,06ecc8994750b45d,d3d200be4d50db06,e7360354ea87f357,cce2e4e0fb0d7b9c,90edde9d8b80cdbf,55b86ca6a5ef6ca8,7ef1a21fded98665,41f251403cbfcd99,f93c4f689c8767d2,3c3740add87e0efa,9ddbef084f17f978,5d45a8677849c1f4,e95a797fd820242c,d8486dd3d3b8bda4,7975ba474e885d4b,1f25a74fd197d0a0,41bb8c56eec1f6b9,14d409f2a3385c86,d3ddcb42a40c2622,097fef42fee0228d,7e7834c5a9ac68be,79780a05ce4d9241,813ff2cda8932c19,0395afb9ae2604ad,9163ca4d6d1b4651,e401426aff4ff7fd,cb2ec182f7fd24fe,61f95c409e8570ab,576458baf1fc21da,f85a0fb8a882b8a4,83790872c5a07cc1,d86a6e4803f68f46,4aa60d0eee37cc95,80001bf36cef42db,42192557410ed220,70dd7463fcfd963a,d2be334fc9e3ae4e,2801e2978dc111c3,57d579ac4e155a29,2ec1724aeaed6b79,db2e27b58007a660,302fc3f555421266,95f550f158f417a8,1b126e1f66a8ecd2,9ca3263e70cfc75c,5f7f379e094cd8dd,61c80c82840e221a,c46688835ca7a23c,41449edb18ca1552,10af1fde4735d77a,379d157eff58acde,d845acb5dd8427e1,7f93214d3f3004a9,9f64104bbd0a5b0d,2058c9e4d99986cf,27c4d0257009081e,40107fa1dce7531c,97d8e4dc93b123cf,99769d4ec12f3a3c,ea934231594684d8,4f382f49501c935c,6e9dc89d1eb98753,c2f63d1de654096f,ced142a8fa0ef22a,46f0b6bed4eac8ec,5df01588f9a7be63,f41ef427446a839f,bd20291e7c8ade38,fb2235cd4cd8510b,67ceb59fc35c5525,de3111b3b6d1008b,52ec538e8e615472,1295d44b779b617a,d8488f10a86034a6,f327d7c508a0bc91,0795b70aa06bdfc7";

        String m="烹然四季,蓬樱咖啡,鹏缘水晶,PENNYBLACK,PENY PHABLON,PERFUME CENTER,PERSONAL POINT,PESERICO,Petaloso,Peter&Angel品臻巧克力,PETIT JARDIN,Petit Pree,Petitprée,PETITS,Peuterey,PeXDi,PH COFFEE,PH7,Philipp Plein,PHILIPPE MODEL,Philosbird,PHILOSOPHY,philosophy,PHO 99,PHO INN粉里越南粉,PHO东田越南粉,PHORM,Ph+,皮尔卡丹,皮尔先生法餐,匹克,匹诺曹意大利手工冰淇淋,琵琶蛮,皮皮狗,披萨馆,皮特曼淘矿小镇,皮特曼淘矿小镇,披头士串烧日本料理,貔貅纯音乐酒吧,皮爷咖啡,偏爱,片断,片段,飘蕾,PIE BIRD,PIER 39,PIERRE MARCOLINI,Piggy,品厨,品馥时光,品高,品珮·手工,品三秦,品尚豆捞,品所生活,品泰,品味长安,品味阳光,品味·南丫,品真阁,平成屋,苹果,平小馆,Pink Memory,PinkBaby,PINTXOS,Pit,PiYOCASA,PizzaExpress,PizzaHut Bistro,PLAIN PEOPLE,PLANETZOO COFFEE,PLANETZOO COFFEE,Play Lounge,PLAY1,PlayABC,PLEIK PHOLPPE,PLEIN SPORT,PLORY,plovence&Queen,珀尔西,珀尔西,破浪潮刺青,泼泼龙,Pocha 19,POGO,POINT DE VUE,POKE POKE,Polestar 2,Police,Polo Ralph Lauren,Polo Ralph Lauren Kids,POLO SPORT,POLO SPORT KIDS,PONY,PONY CLUB,Pony Effect,pop image,POP JUICE,Pop life,popcorn,POPCORN GENERAL STORE,POPITS,POPOKING,poppee,POPPY BLOOM,Popular. Topics,pop´s,POP-UP,POTE,PREE,Prefer you,Premium Japan,Present LIU2,Pretty NANA";

        List<String> code = Arrays.asList(s.split(","));
        List<String> logoname = Arrays.asList(m.split(","));

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < code.size(); i++) {
            map.put(code.get(i),logoname.get(i));
        }
        System.out.println(JSON.toJSONString(map));
    }

    @Test
    public void t(){
        String s="3b9aba02244ace41,9b8b43ea7ef28514,ccedab478e9ab8d8,4ec861a0f20c3cc9,c83d74243efe40c9,935ba47cd247fd88,315dc70dfd13286a,b1754f3b4fc9ab13,e641bd14ccd6402f,04128034b038995b,a818be60c8b75849,dec9165fe2d97a75,503df0bc7c298718,f76cfd3787eebe64,fad6b9c939e17a92,d8182e733d4d3734,3182f44d78916c70,16770a76289a5d1f,af8d7e982f2cb8c5,f476bd0f4ec19676,4f1c7d5a94355796,20f9b61f993bf026,797c79ff2097ad10,ee56c5f14f2ed0f9,9e83b96ffa821432,0a7a0362b4b6a4b9,90018f904d373cfc,9d73d8e24a8bb553,efc42b2767ddcd41,5a768cd151f9eff7,540db886503c1cee,3a9c4496326598b4,6e926723cfa51b66,c62ae57415a737c6,8889a25df0a33ec7,91ef5fa22246d978,e49cda2b741e16f2,1b7bac13c029a24b,383b2c5bad40e49b,06ecc8994750b45d,d3d200be4d50db06,e7360354ea87f357,cce2e4e0fb0d7b9c,90edde9d8b80cdbf,55b86ca6a5ef6ca8,7ef1a21fded98665,41f251403cbfcd99,f93c4f689c8767d2,3c3740add87e0efa,9ddbef084f17f978,5d45a8677849c1f4,e95a797fd820242c,d8486dd3d3b8bda4,7975ba474e885d4b,1f25a74fd197d0a0,41bb8c56eec1f6b9,14d409f2a3385c86,d3ddcb42a40c2622,097fef42fee0228d,7e7834c5a9ac68be,79780a05ce4d9241,813ff2cda8932c19,0395afb9ae2604ad,9163ca4d6d1b4651,e401426aff4ff7fd,cb2ec182f7fd24fe,61f95c409e8570ab,576458baf1fc21da,f85a0fb8a882b8a4,83790872c5a07cc1,d86a6e4803f68f46,4aa60d0eee37cc95,80001bf36cef42db,42192557410ed220,70dd7463fcfd963a,d2be334fc9e3ae4e,2801e2978dc111c3,57d579ac4e155a29,2ec1724aeaed6b79,db2e27b58007a660,302fc3f555421266,95f550f158f417a8,1b126e1f66a8ecd2,9ca3263e70cfc75c,5f7f379e094cd8dd,61c80c82840e221a,c46688835ca7a23c,41449edb18ca1552,10af1fde4735d77a,379d157eff58acde,d845acb5dd8427e1,7f93214d3f3004a9,9f64104bbd0a5b0d,2058c9e4d99986cf,27c4d0257009081e,40107fa1dce7531c,97d8e4dc93b123cf,99769d4ec12f3a3c,ea934231594684d8,4f382f49501c935c,6e9dc89d1eb98753,c2f63d1de654096f,ced142a8fa0ef22a,46f0b6bed4eac8ec,5df01588f9a7be63,f41ef427446a839f,bd20291e7c8ade38,fb2235cd4cd8510b,67ceb59fc35c5525,de3111b3b6d1008b,52ec538e8e615472,1295d44b779b617a,d8488f10a86034a6,f327d7c508a0bc91,0795b70aa06bdfc7";

        String m="烹然四季,蓬樱咖啡,鹏缘水晶,PENNYBLACK,PENY PHABLON,PERFUME CENTER,PERSONAL POINT,PESERICO,Petaloso,Peter&Angel品臻巧克力,PETIT JARDIN,Petit Pree,Petitprée,PETITS,Peuterey,PeXDi,PH COFFEE,PH7,Philipp Plein,PHILIPPE MODEL,Philosbird,PHILOSOPHY,philosophy,PHO 99,PHO INN粉里越南粉,PHO东田越南粉,PHORM,Ph+,皮尔卡丹,皮尔先生法餐,匹克,匹诺曹意大利手工冰淇淋,琵琶蛮,皮皮狗,披萨馆,皮特曼淘矿小镇,皮特曼淘矿小镇,披头士串烧日本料理,貔貅纯音乐酒吧,皮爷咖啡,偏爱,片断,片段,飘蕾,PIE BIRD,PIER 39,PIERRE MARCOLINI,Piggy,品厨,品馥时光,品高,品珮·手工,品三秦,品尚豆捞,品所生活,品泰,品味长安,品味阳光,品味·南丫,品真阁,平成屋,苹果,平小馆,Pink Memory,PinkBaby,PINTXOS,Pit,PiYOCASA,PizzaExpress,PizzaHut Bistro,PLAIN PEOPLE,PLANETZOO COFFEE,PLANETZOO COFFEE,Play Lounge,PLAY1,PlayABC,PLEIK PHOLPPE,PLEIN SPORT,PLORY,plovence&Queen,珀尔西,珀尔西,破浪潮刺青,泼泼龙,Pocha 19,POGO,POINT DE VUE,POKE POKE,Polestar 2,Police,Polo Ralph Lauren,Polo Ralph Lauren Kids,POLO SPORT,POLO SPORT KIDS,PONY,PONY CLUB,Pony Effect,pop image,POP JUICE,Pop life,popcorn,POPCORN GENERAL STORE,POPITS,POPOKING,poppee,POPPY BLOOM,Popular. Topics,pop´s,POP-UP,POTE,PREE,Prefer you,Premium Japan,Present LIU2,Pretty NANA";

        List<String> code = Arrays.asList(s.split(","));
        List<String> logoname = Arrays.asList(m.split(","));

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < code.size(); i++) {
            map.put(logoname.get(i),code.get(i));
        }

        String path="C:\\Users\\EDZ\\Desktop\\许叶童\\许叶童 - 副本";

        File[] files = new File(path).listFiles();
        for (File file : files) {
            String name="";
            String[] split = file.getName().split(".jpg");
            name=map.get(split[0])+".jpg";
            file.renameTo(new File(path+File.separator+name));
        }
    }

    @Test
    public void t2(){
        String s="3b9aba02244ace41,9b8b43ea7ef28514,ccedab478e9ab8d8,4ec861a0f20c3cc9,c83d74243efe40c9,935ba47cd247fd88,315dc70dfd13286a,b1754f3b4fc9ab13,e641bd14ccd6402f,04128034b038995b,a818be60c8b75849,dec9165fe2d97a75,503df0bc7c298718,f76cfd3787eebe64,fad6b9c939e17a92,d8182e733d4d3734,3182f44d78916c70,16770a76289a5d1f,af8d7e982f2cb8c5,f476bd0f4ec19676,4f1c7d5a94355796,20f9b61f993bf026,797c79ff2097ad10,ee56c5f14f2ed0f9,9e83b96ffa821432,0a7a0362b4b6a4b9,90018f904d373cfc,9d73d8e24a8bb553,efc42b2767ddcd41,5a768cd151f9eff7,540db886503c1cee,3a9c4496326598b4,6e926723cfa51b66,c62ae57415a737c6,8889a25df0a33ec7,91ef5fa22246d978,e49cda2b741e16f2,1b7bac13c029a24b,383b2c5bad40e49b,06ecc8994750b45d,d3d200be4d50db06,e7360354ea87f357,cce2e4e0fb0d7b9c,90edde9d8b80cdbf,55b86ca6a5ef6ca8,7ef1a21fded98665,41f251403cbfcd99,f93c4f689c8767d2,3c3740add87e0efa,9ddbef084f17f978,5d45a8677849c1f4,e95a797fd820242c,d8486dd3d3b8bda4,7975ba474e885d4b,1f25a74fd197d0a0,41bb8c56eec1f6b9,14d409f2a3385c86,d3ddcb42a40c2622,097fef42fee0228d,7e7834c5a9ac68be,79780a05ce4d9241,813ff2cda8932c19,0395afb9ae2604ad,9163ca4d6d1b4651,e401426aff4ff7fd,cb2ec182f7fd24fe,61f95c409e8570ab,576458baf1fc21da,f85a0fb8a882b8a4,83790872c5a07cc1,d86a6e4803f68f46,4aa60d0eee37cc95,80001bf36cef42db,42192557410ed220,70dd7463fcfd963a,d2be334fc9e3ae4e,2801e2978dc111c3,57d579ac4e155a29,2ec1724aeaed6b79,db2e27b58007a660,302fc3f555421266,95f550f158f417a8,1b126e1f66a8ecd2,9ca3263e70cfc75c,5f7f379e094cd8dd,61c80c82840e221a,c46688835ca7a23c,41449edb18ca1552,10af1fde4735d77a,379d157eff58acde,d845acb5dd8427e1,7f93214d3f3004a9,9f64104bbd0a5b0d,2058c9e4d99986cf,27c4d0257009081e,40107fa1dce7531c,97d8e4dc93b123cf,99769d4ec12f3a3c,ea934231594684d8,4f382f49501c935c,6e9dc89d1eb98753,c2f63d1de654096f,ced142a8fa0ef22a,46f0b6bed4eac8ec,5df01588f9a7be63,f41ef427446a839f,bd20291e7c8ade38,fb2235cd4cd8510b,67ceb59fc35c5525,de3111b3b6d1008b,52ec538e8e615472,1295d44b779b617a,d8488f10a86034a6,f327d7c508a0bc91,0795b70aa06bdfc7";

        String m="烹然四季,蓬樱咖啡,鹏缘水晶,PENNYBLACK,PENY PHABLON,PERFUME CENTER,PERSONAL POINT,PESERICO,Petaloso,Peter&Angel品臻巧克力,PETIT JARDIN,Petit Pree,Petitprée,PETITS,Peuterey,PeXDi,PH COFFEE,PH7,Philipp Plein,PHILIPPE MODEL,Philosbird,PHILOSOPHY,philosophy,PHO 99,PHO INN粉里越南粉,PHO东田越南粉,PHORM,Ph+,皮尔卡丹,皮尔先生法餐,匹克,匹诺曹意大利手工冰淇淋,琵琶蛮,皮皮狗,披萨馆,皮特曼淘矿小镇,皮特曼淘矿小镇,披头士串烧日本料理,貔貅纯音乐酒吧,皮爷咖啡,偏爱,片断,片段,飘蕾,PIE BIRD,PIER 39,PIERRE MARCOLINI,Piggy,品厨,品馥时光,品高,品珮·手工,品三秦,品尚豆捞,品所生活,品泰,品味长安,品味阳光,品味·南丫,品真阁,平成屋,苹果,平小馆,Pink Memory,PinkBaby,PINTXOS,Pit,PiYOCASA,PizzaExpress,PizzaHut Bistro,PLAIN PEOPLE,PLANETZOO COFFEE,PLANETZOO COFFEE,Play Lounge,PLAY1,PlayABC,PLEIK PHOLPPE,PLEIN SPORT,PLORY,plovence&Queen,珀尔西,珀尔西,破浪潮刺青,泼泼龙,Pocha 19,POGO,POINT DE VUE,POKE POKE,Polestar 2,Police,Polo Ralph Lauren,Polo Ralph Lauren Kids,POLO SPORT,POLO SPORT KIDS,PONY,PONY CLUB,Pony Effect,pop image,POP JUICE,Pop life,popcorn,POPCORN GENERAL STORE,POPITS,POPOKING,poppee,POPPY BLOOM,Popular. Topics,pop´s,POP-UP,POTE,PREE,Prefer you,Premium Japan,Present LIU2,Pretty NANA";

        List<String> code = Arrays.asList(s.split(","));
        List<String> logoname = Arrays.asList(m.split(","));

        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < code.size(); i++) {
            map.put(logoname.get(i),code.get(i));
        }

        String path="C:\\Users\\EDZ\\Desktop\\许叶童\\许叶童 - 副本";

        File[] files = new File(path).listFiles();
        for (File file : files) {
            String name="";
            String[] split = file.getName().split(".jpg");
            if(map.get(split[0])==null){
                System.out.println(split[0]);
            }
//            file.renameTo(new File(path+File.separator+name));
        }
    }

    @Test
    public void m(){
        String name="鹏缘水晶.jpg";
        String[] split = name.split(".jpg");
        System.out.println(split.length);
        System.out.println(name.split(".jpg")[0]);
//        System.out.println(name.split(".")[1]);
    }
}
