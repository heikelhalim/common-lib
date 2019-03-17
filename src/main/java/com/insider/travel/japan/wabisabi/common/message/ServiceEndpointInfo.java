package com.insider.travel.japan.wabisabi.common.message;

/**
 *
 * Reserve the information for service endpoint
 */
public class ServiceEndpointInfo {
    
    public static final String ELB_DNS = "elbDns";
    
    private String elbDns;

    /**
     * 引数の情報からインスタンスを生成する。
     * @param elbDns 各リージョンのELBのDNS(アクセスポイント)（ELBがないテスト環境では、エンドポイント）
     */
    public ServiceEndpointInfo(String elbDns) {
        this.elbDns = elbDns;
    }

    /**
     * 各リージョンのELBのDNS(アクセスポイント)を返します。
     * @return 各リージョンのELBのDNS(アクセスポイント)
     */
    public String getElbDns() {
        return elbDns;
    }

    /**
     * 各リージョンのELBのDNS(アクセスポイント)を設定します。
     * @param elbDns 各リージョンのELBのDNS(アクセスポイント)
     */
    public void setElbDns(String elbDns) {
        this.elbDns = elbDns;
    }

    @Override
    public String toString() {
        return "ServiceEndpoint{" + "elb=" + elbDns + '}';
    }
    
}
