class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet=new HashSet(wordList);
        if(!wordSet.contains(endWord)) return 0;
        Set<String> visited=new HashSet<>(), beginVisited=new HashSet<>(), endVisited=new HashSet<>();
        beginVisited.add(beginWord);
        endVisited.add(endWord);
        int step=1;
        while(!beginVisited.isEmpty()&&!endVisited.isEmpty())
        {
            if(beginVisited.size()>endVisited.size())//选择字符串数量少的一端进行BFS
            {
                Set<String> temp=beginVisited;
                beginVisited=endVisited;
                endVisited=temp;
            }
            Set<String> neighbor=new HashSet<>();//用于存储新的一层的邻接字符串
            for(String aword:beginVisited)
            {
                int len=aword.length();
                char word[]=aword.toCharArray();
                for(int i=0;i<len;i++)
                {
                    for(char c='a';c<='z';c++)
                    {
                        if(word[i]==c) continue;
                        char originChar=word[i];
                        word[i]=c;
                        String nb=String.valueOf(word);
                        word[i]=originChar;//得到更改了一个字符的字符串后，要将其还原，用于尝试更改其他字符
                        if(wordSet.contains(nb))
                        {
                            if(endVisited.contains(nb))
                                return step+1;
                            if(!visited.contains(nb))//令BFS只会遍历未访问的结点，不会再重复操作已访问的字符串
                            {
                                visited.add(nb);
                                neighbor.add(nb);
                            }
                        }
                    }
                }
            }
            beginVisited=neighbor;//更新beginVisited为下一层neighbor
            step++;
        }
        return 0;
    }
}