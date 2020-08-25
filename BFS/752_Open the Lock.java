class Solution {
    public int openLock(String[] deadends, String target) {
        if(target.equals("0000")) return 0;
        int count=0;
        Set<String> begin=new HashSet<>(),end=new HashSet<>();
        Set<String> dead=new HashSet<>();
        Set<String> visited=new HashSet<>();
        for(String s:deadends)
            dead.add(s);
        begin.add("0000");
        end.add(target);
        visited.add("0000");
        visited.add(target);
        while(!begin.isEmpty()&&!end.isEmpty())
        {
            if(begin.size()>end.size())
            {
                Set<String> temp=begin;
                begin=end;
                end=temp;
            }
            Set<String> nextLevel=new HashSet<>();
            for(String s:begin)
            {
                if(dead.contains(s))
                    continue;
                if(end.contains(s)) return count;//一定是错的！！！！！除非是把visited.add也设在取出时
                List<String> neighbors=getNeighbors(s);
                for(String neighbor:neighbors)
                {
                    if(end.contains(neighbor))//因为已经将target设为visited，所以如果neighbor是target，是无法将target加入下一层的，这样就无法在取出时进行判断。并且我们在begin或end加入了中间相遇点的话，那么另一方是永远不可能加入这个点的，因为该点已经设为了visited，所以这个return条件不能写在取出时。
                        return count+1;
                    if(!visited.contains(neighbor))
                    {
                        nextLevel.add(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            begin=nextLevel;
            count++;
        }
        return -1;
    }
    
    public List<String> getNeighbors(String cur)
    {
        List<String> neighbors=new ArrayList<>();
        char array[]=cur.toCharArray();
        for(int i=0;i<4;i++)
        {
            char c=array[i];
            char change1,change2;
            if(c=='0')
            {    
                change1='9';
                change2='1';
            }
            else if(c=='9')
            {
                change1='8';
                change2='0';
            }
            else
            {
                change1=(char)(c+1);
                change2=(char)(c-1);
            }
            array[i]=change1;
            neighbors.add(String.valueOf(array));
            array[i]=change2;
            neighbors.add(String.valueOf(array));
            array[i]=c;
        }
        return neighbors;
    }
}