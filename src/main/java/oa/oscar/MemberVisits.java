package oa.oscar;

import java.util.*;

public class MemberVisits {

    public static Integer get_least_efficient_provider(List<Visit> visits, int diagnosis_code) {

        // provider_id, total_cost / num of visit
        Map<Integer, Avg> map = new HashMap<>();
        for (int i = 0; i < visits.size(); i++) {
            Visit v = visits.get(i);

            if (v.diagnosis_code == diagnosis_code) {
                float sum = 0;
                int num = 0;
                if (map.containsKey(v.provider_id)) {
                    sum = map.get(v.provider_id).sum;
                    num = map.get(v.provider_id).num;
                }
                map.put(v.provider_id, new Avg(sum += v.total_cost, num++));
            }
        }

        float max = Float.MIN_VALUE;
        int providerId = -1;
        for (Map.Entry<Integer, Avg> entry : map.entrySet()) {
            Avg avg = entry.getValue();
            if (avg.sum / avg.num > max) {
                max = avg.sum / avg.num;
                providerId = entry.getKey();
            }
        }

        return providerId;
    }

    //
    static class Avg {

        float sum;
        int num;

        Avg(float sum, int num) {
            this.sum = sum;
            this.num = num;
        }

    }

    // 2 for loop -> O(n^2)
    // max heap -> O(nlogn)
    public static List<Member> topK(List<Member> members, float precentage) {
        if (members == null || members.size() == 0) {
            return Collections.emptyList();
        }

        Queue<MemberCost> queue = new PriorityQueue<>(Comparator.comparing(i -> i.member_total_cost));

        int n = members.size();
        float totalCost = 0;
        // total cost
        for (int i = 0; i < n; i++) {
            Member m = members.get(i);
            int memberTotalCost = 0;
            for (int j = 0; j < m.visits.size(); j++) {
                memberTotalCost += m.visits.get(j).total_cost;
            }

            totalCost += memberTotalCost;
            queue.offer(new MemberCost(m, memberTotalCost));
        }

        List<Member> result = new ArrayList<>();
        float targetCost = totalCost * precentage;

        float tempCost = 0;
        while (!queue.isEmpty()) {
            MemberCost currentMemberCost = queue.poll();
            tempCost += currentMemberCost.member_total_cost;
            result.add(currentMemberCost.member);
            if (tempCost >= targetCost) {
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {

    }

    static class Visit {

        int provider_id;
        int diagnosis_code;
        float total_cost;

    }

    static class Member {

        int member_id;
        List<Visit> visits;

    }

    static class MemberCost {

        Member member;
        float member_total_cost;

        MemberCost(Member member, float member_total_cost) {
            this.member = member;
            this.member_total_cost = member_total_cost;
        }

    }

}
