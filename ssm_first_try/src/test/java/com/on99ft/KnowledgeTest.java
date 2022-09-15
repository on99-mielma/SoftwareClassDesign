package com.on99ft;

import com.on99ft.domain.Article;
import com.on99ft.domain.Doctor;
import com.on99ft.domain.Dtt;
import com.on99ft.domain.Knowledge;
import com.on99ft.service.ArticleService;
import com.on99ft.service.DoctorService;
import com.on99ft.service.DttService;
import com.on99ft.service.KnowledgeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class KnowledgeTest {
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private DttService dttService;

    @Test
    void test0(){
        List<Knowledge> knowledgeList = knowledgeService.getAll();
        for (Knowledge k:
             knowledgeList) {
            System.out.println("k = " + k);
        }
    }

    @Test
    void test1(){
        Knowledge k = new Knowledge();
        k.setTitle("孩子长不高必须要注意这几点");
        k.setText("70%跟遗传因素有关系，30%跟后天环境有关系，要促进生长激素分泌，注意到这3点就可以影响到生长因素分泌\n" +
                "\n" +
                "1饮食这个很重要，一定要补充充足的蛋白质饮食，足够的维生素和矿物质，不能让孩子偏食\n" +
                "\n" +
                "2睡眠的时长和时间点它也是影响生长的，晚上10:00~1:00凌晨3:00~5:00之间，这个时候是生长激素分泌量，比白天还要高，尽量让孩子10点之前上床睡觉\n" +
                "\n" +
                "3多做一些跑和跳的运动，比如说打篮球，踢足球，打排球，或者一些中等强度运动，切记不要做举重和或者负重深蹲这种运动，还有消耗特别大的马拉松\n" +
                "\n" +
                "只要让孩子吃好，睡好，运动好就可以长高");
        knowledgeService.insert(k);
    }
    @Test
    void test2(){
        Knowledge knowledge = knowledgeService.getById(1564832523300245506L);
        System.out.println("knowledge = " + knowledge);
    }

    @Test
    void test3(){
        Article a = new Article();
        a.setAuthor("on99");
        LocalDateTime ldt = LocalDateTime.now();
        a.setDate(ldt);
        a.setText("2022年8月19日，国家标准化罕见病诊疗中心项目启动会在北京协和医院举行。全国政协副主席、农工党中央常务副主席何维出席会议并讲话。该项目旨在通过建设国家标准化、同质化罕见病诊疗协作中心，打造可持续发展的罕见病诊疗和研究体系，全面提升中国罕见病诊疗整体水平。何维副主席指出，在以习近平同志为核心的党中央的坚强领导下，我们打赢了脱贫攻坚战，全面建成了小康社会，进入全面建设社会主义现代化国家新征程。在多发常见病取得良好保障的基础之上，罕见病诊疗和研究领域的突破将成为我国卫生健康现代化的鲜明标志之一。北京协和医院在我国罕见病诊疗保障事业发展进程中发挥了不可替代的引领、示范、带动作用，由协和牵头建立国家标准化罕见病诊疗中心将进一步促进优质资源下沉，推动健康中国建设。他对中心建设提出三点建议：一是重协作，建机制，带动基层罕见病诊疗能力提升；二是重创新，求突破，关注新疗法和新诊断方法的研发和应用；三是重诊疗，求预防，建立适宜我国国情的罕见病预防模式。近年来，党和国家高度重视罕见病患者群体，出台多项政策“不放弃每一个小群体”，罕见病诊疗、研究、保障等多方面实现了跨越式发展，但不少罕见病患者依然面临“诊断难、治疗难、治疗贵”的难题。罕见病的有效防治是健康中国建设的重要组成部分，全面提升基层医疗机构的罕见病诊疗能力，建立标准化、同质化的罕见病诊疗中心势在必行。\n" +
                "\n" +
                "此次启动的国家标准化罕见病诊疗中心，将以1个全国中心——北京协和医院罕见病诊疗中心为支撑平台，在全国铺设包括中西部地市级医院在内的50个重点培育中心，搭建国家标准化罕见病诊疗示范体系，涵盖罕见病多学科协作分级诊疗、诊疗能力培训和健康教育三大任务。借助5G和人工智能等前沿信息技术提升基层医疗机构诊疗能力，构建覆盖全国的协和罕见病标准诊疗和教育联盟，提升基层区域中心的罕见病诊疗水平，探索优质医疗资源下沉机制，真正惠及罕见病患者。协和在建设国家标准化罕见病诊疗中心的进程中，将着力加快科技创新转化、加强人才培养，推动罕见病诊疗管理体系建设，聚各方力量让罕见病患者得到更多救助。\n" +
                "\n" +
                "启动仪式上，在全国政协副主席、农工党中央常务副主席何维，中国工程院院士张学，北京协和医院院长张抒扬的共同见证下，北京协和医院副院长杜斌与中国国际投资促进会副会长、中国初级卫生保健基金会理事狄森分别代表医院与中国初级卫生保健基金会签署合作协议。签约仪式后，国家卫生健康委科教司监察专员刘登峰和北京协和医院党委书记吴沛新为项目揭牌。伴随与会嘉宾在大屏幕上按下启动键，国家标准化罕见病诊疗中心项目正式启动。");
        a.setTitle("精准帮扶工程 | 国家标准化罕见病诊疗中心在北京协和医院启动");
        articleService.insert(a);
    }

    @Test
    void test4(){
        List<Article> articleList = articleService.selectAll();
        for (Article w:
                articleList) {
            w.setText((w.getText().substring(0,15)));
        }
        for (Article a:
             articleList) {
            System.out.println("a = " + a);
        }
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("ldt = " + ldt);
    }
    @Test
    void test5(){
        Doctor d = new Doctor();
        d.setName("66uo");
        d.setOffice("心脏内科");
        d.setInfo("1");
        d.setSkill("1");
        doctorService.insert(d);
        System.out.println("d = " + d);
    }

    @Test
    void test6(){
        Dtt dtt = new Dtt();
        dtt.setId(1570252461800316930L);
        dttService.insert(dtt);
        System.out.println("dtt = " + dtt);
    }
}
