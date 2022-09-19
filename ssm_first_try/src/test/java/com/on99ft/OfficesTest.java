package com.on99ft;

import com.on99ft.domain.Offices;
import com.on99ft.service.OfficesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OfficesTest {
    @Autowired
    private OfficesService officesService;

    @Test
    void test1(){
        Offices offices =new Offices();
        officesService.insert(offices);
    }
    @Test
    void test(){
        List<Offices> offices = officesService.SelectAll();
        System.out.println("offices = " + offices);
    }

    @Test
    void test2(){
        Offices offices =new Offices();
        offices.setOfficeName("变态反应科");
        offices.setOfficeInfo("北京协和医院变态反应科是中国临床变态反应学的发源地和摇篮。\n" +
                "\n" +
                "　　1938年，我院张庆松教授赴美深造，在学习耳鼻喉的同时学习变态反应。张教授1939年归国后，拟在当时的北京协和医院筹建一个变态反应门诊，但因当时我国处于抗日战争时期而被迫中断。\n" +
                "\n" +
                "　　新中国成立后，从1952年开始，张庆松教授率领叶世泰在物质匮乏，条件极其艰苦的条件下筹建变态反应科。建国之初，要建一个在中国从未出现过的变态反应科，真是白手起家。叶世泰利用业余时间到图书馆去查找和学习资料，了解变态反应学的基本知识和现状，通过全面学习了当时能找到的所有资料。叶教授回忆说：“当年的协和医院图书馆真是日夜门庭若市，晚上闭馆时总要多次打铃，再三高声催促‘要关门了’，人们才合上书本迟迟离去。通过大量的阅读，我发现变态反应学在整个医学中真是一朵奇葩，她包罗万象，奥妙无穷，更激发了我对这门新兴学科的好感与兴趣。”\n" +
                "\n" +
                "　　当时，全北京没有一家像样的医疗设备商店，变态反应科所需设备，一些制备变态反应诊断和治疗用抗原提取液所需的器械如蔡氏无菌滤器、分液漏斗、分溜器均无从购买，即使对在变态反应科来说必备的冰箱，当年亦是走遍北京无觅处，最后还是从旧货商店里买回来了一台“二手货”。工夫不负有心人，在多方努力之下，蔡氏滤器在医院器材库的废品堆里找到了，分液漏头、分溜器、分析天秤等亦先后找到了，各种必要的玻璃仪器也陆续初步配备完成了。1956年5月1日，中国第一个变态反应科在北京协和医院正式创建。\n" +
                "\n" +
                "　　北京协和医院变态反应科在历届主任张庆松、叶世泰和张宏誉教授的领导下，几十年来，在医疗、科研、教学和中华医学会变态反应学分会的创建等方面做了许多开创性的工作，对中国变态反应事业的发展做出了重要贡献。\n" +
                "\n" +
                "　　变态反应科从建科伊始，即吸引来了大量的病人，不但有耳鼻喉科的变态反应病人，而且有大量耳鼻喉科以外的变态反应病人，如支气管哮喘、药物过敏、食物过敏、各种过敏性皮肤病等，病种非常丰富。就在变态反应科正式成立并接受病人的第二年，临床工作中就发现了一批呼吸道过敏的病人，这些病人的发病有明显的季节性，高潮在秋季，这引起了张庆松、叶世泰和顾瑞金的注意，认为这些病人很可能是由花粉过敏引起的。经过连续多年的北京地区的空气中花粉调查，深入的病情观察，病人的家庭和工作环境调查，北京郊区的植被调查，直到六十年代初，通过可疑致敏花粉的鼻腔内激发试验，终于证实了在我国北方地区的重要致敏花粉是蒿类植物的花粉，从而奠定了中国花粉变态反应研究的基础。\n" +
                "\n" +
                "　　1957年春夏之交，一场惊心动魄的反右运动开始，从此政治运动一个接着一个。变态反应学由于是发源于西方的一门新兴医学，并在诊治工作中又非常重视病人的生活环境和生活方式、医疗中常常要提出一些对病人衣食住行各方面的特殊要求，一时竟成了受批判的罪证，斥之为宣扬资产阶级生活方式，对病人提出的生活中对过敏源的禁忌则斥之为清规戒律。从此每次政治运动，变态反应科均不免遭到一些非议。直到1966年史无前例的“文革”兴起，变态反应科成了受冲击的重点，称之为资产阶级思想的堡垒，培养资产阶级接班人的据点。日常业务受到极大扰乱，几乎到了无法继续工作的境地。叶世泰教授回忆说：“眼看着大量变态反应病人对治疗的迫切要求，我暗自告诚，条件再困难，工作亦不能中断，即使在最困难的时候，科里只剩下了我一个人，还是把门诊、皮肤试验、脱敏治疗等工作全部坚持了下来，在整个文革期间，变态反应科的工作始终没有中断过一天，取得了病人的信任和支持。”\n" +
                "\n" +
                "　　1971年夏季的一天，叶世泰教授等从事呼吸疾病的医务人员一道得到了周恩来总理的接见。在会上，叶世泰教授就当时在气管炎防治中重视对症治疗，忽视病因治疗，以及针对当时将一切有咳、痰、喘症状的病人均诊为气管炎的做法谈了自己的意见，并将由花粉引起的咳喘即使在春夏秋季亦可大量发病等情况向总理作了报告。总理做出批示：“大家回去后要立即投入工作，不要等到冬季再开展工作。”总理的接见引起了卫生部对变态反应学的重视，并在以后又多次指示：为了保持协和医院的特色，变态反应科不能取消，还要继续发展。\n" +
                "\n" +
                "　　七十年代，通过长期家庭及粮店的现场调查，在从病人家庭和粮店收集的尘土中第一次发现尘螨，为我国尘螨过敏的临床和基础研究奠定了基础。\n" +
                "\n" +
                "　　八十年代，北京协和医院变态反应科牵头完成了全国气传致敏花粉调查，初步摸清了我国各省及地区致敏花粉的分布情况。为我国花粉症的临床诊断和治疗工作奠定了基础。紧随其后，北京协和医院变态反应科再次牵头完成了全国空气中霉菌的调查，为我国霉菌过敏性疾病的诊治和研究奠定了基础。\n" +
                "\n" +
                "　　协和医院以解决疑难杂症的著名医院，变态反应科的医生着眼于临床，完成了许多在中国前所未有的工作：\n" +
                "\n" +
                "　　1980年代，我科张宏誉教授在中国率先开始了遗传性血管神经性水肿的研究，迄今已收集来自90多个家系的360多例病人，在国际上也是病例数较多的临床研究中心。对HAE的发病机制、临床特点及治疗进行了深入研究，对此领域的研究已达国际领先水平，并于200？年发现了HAE新的基因突变类型，并就相关研究结果进行了报道和国际学术交流。");
        officesService.insert(offices);
    }
}
