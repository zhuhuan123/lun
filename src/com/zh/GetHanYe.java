package com.zh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;

public class GetHanYe {
	public static void main(String[] args) throws Exception {
		
		
		HashSet<String> stopWords = new HashSet<String>();
		String line ="房地产|开发区|高科技|天然气|新技术|农产品|变压器|混凝土|电极箔|进出口|新材料|化学|印染|纺织|生物|保健|商务|泵业|包装|燃料|能源|资源|有色|金属|船务|港务|港业|印务|高速|公路|医药|运输|食品|生态|环保|农业|农药|化肥|饲料|米业|粮油|水泥|出租|汽车|安装|建筑|建材|服务|发电|塑业|零售|连锁|药房|智能|控制|经销|购物|广场|交易|中心|电力|销售|锂业|配件|物流|国际|装饰|建设|模具|医疗|装备|通信|数据|商贸|制造|经营|药业|制药|置业|实业|创业|热力|热电|化工|教育|企业|发展|矿业|塑料|纸业|石油|材料|数控|设备|仓储|工贸|软件|电器|仪器|仪表|成套|货物|代理|科贸|机电|信息|精密|机械|煤业|玻璃|国际|对外|经济|贸易|经贸|物业|管理|水电|铸造|日化|电子|科技|工程|工业|电器|电气|电池|装潢|广告|空调|设备|开发|技术|供应|地产|餐饮|投资|咨询|顾问|综合|超市|资产|评估|营销|证券|经纪|黄金|白银|劳动|社会|保障|风险|信用|担保|拍卖|旅行|医学|银行|保险|研究|干细胞|基因|制品|商城|民爆|牧业|批发|文化|传播|空间|联合|电线|电缆|煤业|天然|时装|塑业|糖业|酒业|储备|电信|门诊|医疗|合金|非晶|公共|事业|系统|商业|产业|汽水|饮料|管道|种植|养殖|林木|风电|农副|家具|产品|制衣|针织|检测|质量|车桥|包装|中药|航运|饮用水|健康|用品|车床|橡塑|机床|电脑|宽频|果业|电工|器材|铝|镁|铁|铜|钢|铅|锂";
		String [] strings = line.split("\\|");
		for(String string :strings){
			stopWords.add(string);
		}
		
		
		
//		FileInputStream inputStream = new FileInputStream(
//				new File("E:\\work\\WanLongCom4\\msg11\\jian.txt"));
//		if(inputStream!=null){
//			BufferedReader bufferedReader = new BufferedReader (new InputStreamReader(inputStream));
//			String line = bufferedReader.readLine();
//			while(line!=null){
//				System.out.println(line);
//				String [] strings = line.split("|");
//				for(String string :strings){
//					stopWords.add(string);
//				}
//				line = bufferedReader.readLine();
//			}
//		}
		System.out.println(stopWords);
	}
}
