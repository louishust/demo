package com.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Hello world!
 *
 */
public class App 
{
     public static void main(String[] args) {
        String data = """
西岗镇	49793	4729763	51843	1843711
新城街道	21810	527116	2474469	2736191
木石镇	27727	23716750	336059	814510
邹坞镇	28891	10875630	460071	4506553
龙泉街道	28386	2576670	1035889	2131187
北辛街道	24887	1696209	1037201	6794387
兴仁街道	14741	4203602	1244629	6381356
常庄街道	15148	12496878	324287	1456948
西王庄镇	20082	4270043	187465	1500044
荆河街道	16901	5757929	248713	2739639
兴城街道	9909	3479091	1191529	380087
善南街道	14263	5903588	231629	1748835
临城街道	9785	1595957	724918	4506553
税郭镇	11149	3339893	366023	4199061
张范街道	9272	5483114	169923	9144511
滨湖镇	15561	931282	81668	1145550
东郭镇	12254	7380402	46221	210773
阴平镇	12502	2428890	283193	834628
永安镇	2589	3742957	1278766	1055642
马兰屯镇	8548	8928229	270201	1101914
文化路街道	2895	348064	1345377	1552644
陶庄镇	9458	1842549	326815	3309151
洪绪镇	8253	4060774	36470	8561517
光明街道	5542	736839	703679	3188074
坛山街道	8723	1262796	416380	760943
南沙河镇	10269	1915515	138564	401747
运河街道	5096	2939881	541704	1751442
榴园镇	4740	5780221	131416	4973951
鲍沟镇	8574	3462902	47024	594494
级索镇	8306	1262923	180801	743074
各塔埠街道	2418	1115741	607824	3544963
涧头集镇	7441	2321462	69352	887213
齐村镇	5731	1862547	205797	1491940
东沙河街道	6783	871577	89658	157629
大坞镇	6253	1179714	33357	355570
姜屯镇	5576	1370374	101347	214558
峨山镇	3289	5419107	45557	925846
孟庄镇	3278	1641775	298431	612636
官桥镇	5640.76	962305	77880	355451
山城街道	3584	859525	274131	786352
沙沟镇	4358	474777	201278	858340
龙山路街道	2004	550476	430844	1117149
张汪镇	4898	1259224	67366	695157
吴林街道	4588	1148080	117460	325645
界河镇	5210	704385	49949	480144
羊庄镇	4921.63	422489	88906	216859
古邵镇	3400	1345416	153802	311944
西集镇	4008.55	2078425	19731	119142
邳庄镇	4066	671884	62429	314047
张山子镇	2552	2358305	52762	1102850
龙阳镇	3955	515997	37346	109432
底阁镇	3249	471715	73221	72554
桑村镇	2321	2217494	9409	234723
中心街街道	2639	331372	83081	280356
北庄镇	3011	714017	27429	96988
泥沟镇	2259	903813	58165	634347
柴胡店镇	2332	379624	90974	109219
周营镇	1469	652017	128135	106654
徐庄镇	1514	545118	97562	2652
冯卯镇	1545	391862	88542	90493
城头镇	1635.4	716660	38069	334278
矿区街道	1173	684713	67820	604475
凫城镇	1452	786754	5157	123765
水泉镇	1648	254853	10582	37173
店子镇	792.92	690329	42596	171403
""";

        List<Township> townships = new ArrayList<>();
        String[] lines = data.strip().split("\n");

        for (String line : lines) {
            String[] parts = line.split("\\s+");
            String name = parts[0];
            double attr1 = Double.parseDouble(parts[1]);
            double attr2 = Double.parseDouble(parts[2]);
            double attr3 = Double.parseDouble(parts[3]);
            double attr4 = Double.parseDouble(parts[4]);
            townships.add(new Township(name, attr1, attr2, attr3, attr4));
        }

         // 打印总数
         System.out.println("总数: " + townships.size());

         // 打印结果
         for (Township township : townships) {
             System.out.println(township);
         }
 
         // 检查结果
         for (Township township : townships) {
             if (township.getAttr1() < 792.92 || township.getAttr1() > 49793) {
                 System.out.println("attr1不在范围内: " + township);
             }
             if (township.getAttr2() < 254853 || township.getAttr2() > 23716750) {
                 System.out.println("attr2不在范围内: " + township);
             }
             if (township.getAttr3() < 5157 || township.getAttr3() > 2474469) {
                 System.out.println("attr3不在范围内: " + township);
             }
             if (township.getAttr4() < 2652 || township.getAttr4() > 9144511) {
                 System.out.println("attr4不在范围内: " + township);
             }
         }

        List<String> targetNames = List.of("西岗镇", "新城街道", "木石镇", "邹坞镇", "龙泉街道", "北辛街道", "兴仁街道", "常庄街道", "西王庄镇", "荆河街道");


        BlockingQueue<double[]> queue = new LinkedBlockingQueue<>();


        Thread producerThread = new Thread(new Producer(queue));
        producerThread.start();


        List<Thread> consumerThreads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            // 每个消费者线程都有一个新的townships列表， 深拷贝
            List<Township> newTownships = new LinkedList<>();
            for (Township township : townships) {
                newTownships.add(new Township(township.getName(), township.getAttr1(), township.getAttr2(), township.getAttr3(), township.getAttr4()));
            }

            Thread consumerThread = new Thread(new Consumer(queue, newTownships, targetNames));
            consumerThread.start();
            consumerThreads.add(consumerThread);
        }

        try {
            producerThread.join();
            for (Thread consumerThread : consumerThreads) {
                consumerThread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
