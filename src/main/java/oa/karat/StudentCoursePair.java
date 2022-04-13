package oa.karat;

import java.util.*;

/*
You are a developer for a university. Your current project is to develop a system for students to find courses they share with friends. The university has a system for querying courses students are enrolled in, returned as a list of (ID, course) pairs.

Write a function that takes in a collection of (student ID number, course name) pairs and returns, for every pair of students, a collection of all courses they share.


Sample Input:

student_course_pairs_1 = [
 ["58", "Linear Algebra"],
 ["94", "Art History"],
 ["94", "Operating Systems"],
 ["17", "Software Design"],
 ["58", "Mechanics"],
 ["58", "Economics"],
 ["17", "Linear Algebra"],
 ["17", "Political Science"],
 ["94", "Economics"],
 ["25", "Economics"],
 ["58", "Software Design"],
]

Sample Output (pseudocode, in any order):

find_pairs(student_course_pairs_1) =>
{
 "58,17": ["Software Design", "Linear Algebra"]
 "58,94": ["Economics"]
 "58,25": ["Economics"]
 "94,25": ["Economics"]
 "17,94": []
 "17,25": []
}


Additional test cases:

Sample Input:

student_course_pairs_2 = [
 ["0", "Advanced Mechanics"],
 ["0", "Art History"],
 ["1", "Course 1"],
 ["1", "Course 2"],
 ["2", "Computer Architecture"],
 ["3", "Course 1"],
 ["3", "Course 2"],
 ["4", "Algorithms"]
]



Sample output:

find_pairs(student_course_pairs_2) =>
{
 "1,0":[]
 "2,0":[]
 "2,1":[]
 "3,0":[]
 "3,1":["Course 1", "Course 2"]
 "3,2":[]
 "4,0":[]
 "4,1":[]
 "4,2":[]
 "4,3":[]
}

Sample Input:
student_course_pairs_3 = [
 ["23", "Software Design"],
 ["3", "Advanced Mechanics"],
 ["2", "Art History"],
 ["33", "Another"],
]


Sample output:

find_pairs(student_course_pairs_3) =>
{
 "23,3": []
 "23,2": []
 "23,33":[]
 "3,2":  []
 "3,33": []
 "2,33": []
}

Complexity analysis variables:

n: number of student,course pairs in the input
s: number of students
c: total number of courses being offered (note: The number of courses any student can take is bounded by a small constant)
*/


public class StudentCoursePair {
    public static void main(String[] argv) {
        String[][] studentCoursePairs1 = {
                {"58", "Linear Algebra"},
                {"94", "Art History"},
                {"94", "Operating Systems"},
                {"17", "Software Design"},
                {"58", "Mechanics"},
                {"58", "Economics"},
                {"17", "Linear Algebra"},
                {"17", "Political Science"},
                {"94", "Economics"},
                {"25", "Economics"},
                {"58", "Software Design"}
        };

        String[][] studentCoursePairs2 = {
                {"0", "Advanced Mechanics"},
                {"0", "Art History"},
                {"1", "Course 1"},
                {"1", "Course 2"},
                {"2", "Computer Architecture"},
                {"3", "Course 1"},
                {"3", "Course 2"},
                {"4", "Algorithms"}
        };

        String[][] studentCoursePairs3 = {
                {"4", "Software Design"},
                {"2",  "Advanced Mechanics"},
                {"1",  "Art History"},
                {"3", "Another"},
        };

        find_pairs(studentCoursePairs3);
    }

    private static Map<String, List<String>> find_pairs(String[][] pairs) {
        Map<String, List<String>> result = new HashMap<>();
        // build course list
        Set<String> ids = new HashSet<>();
        Map<String, List<String>> course2Students = new HashMap<>();
        for (int i = 0; i < pairs.length; i++) {
            String id = pairs[i][0];
            String course = pairs[i][1];
            if (!course2Students.containsKey(course)) {
                course2Students.put(course, new ArrayList<String>());
            }
            List<String> currentStudentIds = course2Students.get(course);
            currentStudentIds.add(id);
            ids.add(id);
        }
        System.out.println(course2Students);
        System.out.println(ids);


        // build pair
        List<List<String>> pairIds = new ArrayList<>();

        List<String> idList = new ArrayList<>(ids);
        // [1, 2, 3]
        // [[1,2], [1,3], [2,3]]
        for (int i = 0; i < idList.size(); i++) {
             // 1
            for (int j = i + 1; j < idList.size(); j++) {
                List<String> pair = new ArrayList<>();
                pair.add(idList.get(i)); // 2 -> 1,2
                pair.add(idList.get(j)); // 2 -> 1,2
                pairIds.add(pair);
//                pair.remove(pair.size() - 1);
            }
        }
        System.out.println(pairIds);


//        Queue<String> queue = new LinkedList<>(ids);
//        while(!queue.isEmpty()) {
//            String currentId = queue.poll();
//            for (int i = 0; i < ids.size(); i++) {
//                String nextId = idlist.get(i);
//                if (currentId != nextId) {
//                    List<String> newList = Arrays.asList(currentId,nextId);
//                    pairIds.add(newList);
//                }
//            }
//        }
//        System.out.println(pairIds);

        // find common
//        for (int i = 0; i < pairIds.size(); i++) {
//            List<String> pair = pairIds.get(i);
//            String id1 = pair.get(0);
//            String id2 = pair.get(1);
//            for (Map.Entry<String, List<String>> entry: course2Students.entrySet())  {
//                List<String> idList = entry.getValue();
//                idList.contains(id1) &&
//                        entry.getKey()
//            }
//        }

        return result;
    }

}

