## java中的Collection
> 三种子类型    
Collection -> List -> LinkedList 没有同步方法   
Collection -> List -> ArrayList 非同步的（unsynchronized）   
Collection -> List -> Vector(同步) 非常类似ArrayList，但是Vector是同步的    
Collection -> List -> Vector -> Stack  push和pop方法，还有peek方法得到栈顶的元素，empty方法测试堆栈是否为空，search方法检测一个元素在堆栈中的位置。注意：Stack刚创建后是空栈。     
Collection -> Set(没有重复元素) -> EnumSet 是枚举的专用Set。所有的元素都是枚举类型。    
Collection -> Set(没有重复元素) -> HashSet 查询速度最快的集合，因为其内部是以HashCode来实现的。它内部元素的顺序是由哈希码来决定的，所以它不保证set 的迭代顺序；特别是它不保证该顺序恒久不变。
Collection -> Set(没有重复元素) -> SortSet     
Collection -> Set(没有重复元素) -> SortSet -> TreeSet  自然排序，可以实现comparable接口然后进行元素的排序。  
Collection -> Map(没有实现Collection接口) -> HashMap   可空的对象。不同步的 ，但是效率高，较常用。 注：迭代子操作时间开销和HashMap的容量成比例。因此，如果迭代操作的性能相当重要的话，不要将HashMap的初始化容量设得过高，或者load factor过低。
Collection -> Map(没有实现Collection接口) -> Hashtable 任何非空（non-null）的对象。同步的          
Collection -> Map(没有实现Collection接口) -> HashMap -> WeakHashMap  改进的HashMap，它对key实行“弱引用”，如果一个key不再被外部所引用，那么该key可以被GC回收。     
Collection -> Map(没有实现Collection接口) -> SortMap-> TreeMap       键以某种排序规则排序，内部以red-black（红-黑）树数据结构实现，实现了SortedMap接口

Collection -> Queue
> 队列，它主要分为两大类，一类是阻塞式队列，队列满了以后再插入元素则会抛出异常，主要包括ArrayBlockQueue、PriorityBlockingQueue、LinkedBlockingQueue。
另一种队列则是双端队列，支持在头、尾两端插入和移除元素，主要包括：ArrayDeque、LinkedBlockingDeque、LinkedList。

## int[]数组的创建
> int[] nums =  new int[]{0,6,8,4}。

## 什么时候使用length()和size()
> String使用的是length，数组使用的是length，List使用的是size。


# ArrayList/Vector/LinkedList 的底层分析
## ArrayList(基于数组实现)
`ArrayList` 实现于 `List`、`RandomAccess` 接口。可以插入空数据，也支持随机访问。
- 随着元素的增加，数组的大小可以自动的增加。
也就是说，如果要是添加大数量的实例对象，可以先对数组手动进行容量检查(ensureCapacity)，这样会减少`增量的重分配`(increment reallocation)的次数。

- 并且必须要在创建的时候进行同步化操作，防止意外的非同步化操作。
由于是未加锁的，所以为了保证多线程之间的安全性和一致性，可以在一个同步对象中封装这个集合，也可以通过`Collections.synchronizedList`方法对这个集合进行同步化。

- ArrayList是[fail-fast](interview-java-Q&A.md)为了防止不确定性。
该集合使用了`modcount`更改次数来保证迭代器的并发更新异常，每次调用next()之前都会进行`checkForComodification`检查。

- `ArrayList `相当于动态数据，其中最重要的两个属性分别是: `elementData` 数组，以及 `size` 大小。
在调用 `add()` 方法的时候
``` java_holder_method_tree
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
```

- 首先进行扩容校验。
- 将插入的值放到尾部，并将 size + 1 。

如果是调用 `add(index,e)` 在指定位置添加的话：
```java_holder_method_tree
public void add(int index, E element) {
	rangeCheckForAdd(index);
	ensureCapacityInternal(size + 1);  // Increments modCount!!
	//复制，向后移动
	System.arraycopy(elementData, index, elementData, index + 1,
					 size - index);
	elementData[index] = element;
	size++;
}
```

- 也是首先扩容校验。
- 接着对数据进行复制，目的是把 index 位置空出来放本次插入的数据，并将后面的数据向后移动一个位置。

其实扩容最终调用的代码:
```java_holder_method_tree
private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity;
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
    }
```

也是一个数组复制的过程。
由此可见 `ArrayList` 的主要消耗是数组扩容以及在指定位置添加数据，在日常使用时最好是指定大小，尽量减少扩容。更要减少在指定位置插入数据的操作。

### ArrayList的序列化

由于 ArrayList 是基于动态数组实现的，所以并不是所有的空间都被使用。因此使用了 `transient` 修饰，可以防止被自动序列化。

```java_holder_method_tree
transient Object[] elementData;
```

因此 ArrayList 自定义了序列化与反序列化：

```java_holder_method_tree
private void writeObject(java.io.ObjectOutputStream s)
	throws java.io.IOException{
	// Write out element count, and any hidden stuff
	int expectedModCount = modCount;
	s.defaultWriteObject();

	// Write out size as capacity for behavioural compatibility with clone()
	s.writeInt(size);

	// Write out all elements in the proper order.
	//只序列化了被使用的数据
	for (int i=0; i<size; i++) {
		s.writeObject(elementData[i]);
	}

	if (modCount != expectedModCount) {
		throw new ConcurrentModificationException();
	}
}

private void readObject(java.io.ObjectInputStream s)
	throws java.io.IOException, ClassNotFoundException {
	elementData = EMPTY_ELEMENTDATA;

	// Read in size, and any hidden stuff
	s.defaultReadObject();

	// Read in capacity
	s.readInt(); // ignored

	if (size > 0) {
		// be like clone(), allocate array based upon size not capacity
		ensureCapacityInternal(size);

		Object[] a = elementData;
		// Read in all elements in the proper order.
		for (int i=0; i<size; i++) {
			a[i] = s.readObject();
		}
	}
}
private void writeObject(java.io.ObjectOutputStream s)
	throws java.io.IOException{
	// Write out element count, and any hidden stuff
	int expectedModCount = modCount;
	s.defaultWriteObject();

	// Write out size as capacity for behavioural compatibility with clone()
	s.writeInt(size);

	// Write out all elements in the proper order.
	//只序列化了被使用的数据
	for (int i=0; i<size; i++) {
		s.writeObject(elementData[i]);
	}

	if (modCount != expectedModCount) {
		throw new ConcurrentModificationException();
	}
}

private void readObject(java.io.ObjectInputStream s)
	throws java.io.IOException, ClassNotFoundException {
	elementData = EMPTY_ELEMENTDATA;

	// Read in size, and any hidden stuff
	s.defaultReadObject();

	// Read in capacity
	s.readInt(); // ignored

	if (size > 0) {
		// be like clone(), allocate array based upon size not capacity
		ensureCapacityInternal(size);

		Object[] a = elementData;
		// Read in all elements in the proper order.
		for (int i=0; i<size; i++) {
			a[i] = s.readObject();
		}
	}
}
```

> 当对象中自定义了 writeObject 和 readObject 方法时，JVM 会调用这两个自定义方法来实现序列化与反序列化。

从实现中可以看出 ArrayList 只序列化了被使用的数据。

## Vector
>`Voctor` 也是实现于 `List` 接口，底层数据结构和 `ArrayList` 类似,也是一个动态数组存放数据。
不过是在 `add()` 方法的时候使用 `synchronize` 进行同步写数据，但是开销较大，所以 `Vector` 是一个同步容器并不是一个并发容器。

以下是 `add()` 方法：
```java_holder_method_tree
    public synchronized boolean add(E e) {
        modCount++;
        ensureCapacityHelper(elementCount + 1);
        elementData[elementCount++] = e;
        return true;
    }
```

以及指定位置插入数据:
```java_holder_method_tree
    public void add(int index, E element) {
        insertElementAt(element, index);
    }
    public synchronized void insertElementAt(E obj, int index) {
        modCount++;
        if (index > elementCount) {
            throw new ArrayIndexOutOfBoundsException(index
                                                     + " > " + elementCount);
        }
        ensureCapacityHelper(elementCount + 1);
        System.arraycopy(elementData, index, elementData, index + 1, elementCount - index);
        elementData[index] = obj;
        elementCount++;
    }
```

## LinkedList(基于链表实现)
![](https://ws4.sinaimg.cn/large/006tKfTcly1fqzb66c00gj30p7056q38.jpg)
如图所示 `LinkedList` 底层是基于双向链表实现的，也是实现了 `List` 接口，所以也拥有 List 的一些特点(JDK1.7/8 之后取消了循环，修改为双向链表)。

## 新增方法
```java_holder_method_tree
public boolean add(E e) {
    linkLast(e);
    return true;
}
 /**
 * Links e as last element.
 */
void linkLast(E e) {
    final Node<E> l = last;
    final Node<E> newNode = new Node<>(l, e, null);
    last = newNode;
    if (l == null)
        first = newNode;
    else
        l.next = newNode;
    size++;
    modCount++;
}
```

可见每次插入都是移动指针，和 ArrayList 的拷贝数组来说效率要高上不少。

## 查询方法

```java_holder_method_tree
public E get(int index) {
	checkElementIndex(index);
	return node(index).item;
}

Node<E> node(int index) {
	// assert isElementIndex(index);

	if (index < (size >> 1)) {
		Node<E> x = first;
		for (int i = 0; i < index; i++)
			x = x.next;
		return x;
	} else {
		Node<E> x = last;
		for (int i = size - 1; i > index; i--)
			x = x.prev;
		return x;
	}
}
```

由此可以看出是使用二分查找来看 `index` 离 size 中间距离来判断是从头结点正序查还是从尾节点倒序查。

- `node()`会以`O(n/2)`的性能去获取一个结点
    - 如果索引值大于链表大小的一半，那么将从尾结点开始遍历

这样的效率是非常低的，特别是当 index 越接近 size 的中间值时。

总结：

- LinkedList 插入，删除都是移动指针效率很高。
- 查找需要进行遍历查询，效率较低。

# HashSet和HashMap(无序的HashMap)
> `HashSet` 是一个不允许存储重复元素的集合，它的实现比较简单，只要理解了 `HashMap`，`HashSet` 就水到渠成了。
   
## 成员变量
> 首先了解下 `HashSet` 的成员变量:

```java_holder_method_tree
   private transient HashMap<E,Object> map;

   // Dummy value to associate with an Object in the backing Map
   private static final Object PRESENT = new Object();
```
> 发现主要就两个变量:
- `map` ：用于存放最终数据的。
- `PRESENT` ：是所有写入 map 的 `value` 值。

## 构造函数
```java_holder_method_tree
   public HashSet() {
       map = new HashMap<>();
   }
   
   public HashSet(int initialCapacity, float loadFactor) {
       map = new HashMap<>(initialCapacity, loadFactor);
   }    
```
构造函数很简单，利用了 `HashMap` 初始化了 `map` 。

## add
```java_holder_method_tree
   public boolean add(E e) {
       return map.put(e, PRESENT)==null;
   }
```

比较关键的就是这个 `add()` 方法。
可以看出它是将存放的对象当做了 `HashMap` 的健，`value` 都是相同的 `PRESENT` 。由于 `HashMap` 的 `key` 是不能重复的，所以每当有重复的值写入到 `HashSet` 时，`value` 会被覆盖，但 `key` 不会收到影响，这样就保证了 `HashSet` 中只能存放不重复的元素。

## 总结
`HashSet` 的原理比较简单，几乎全部借助于 `HashMap` 来实现的。   
所以 `HashMap` 会出现的问题 `HashSet` 依然不能避免。


## 无序的HashMap
### HashMap 底层分析
![](https://ws2.sinaimg.cn/large/006tNc79gy1fn84b0ftj4j30eb0560sv.jpg)
如图所示，HashMap 底层是基于数组和链表实现的。其中有两个重要的参数：
- 容量
- 负载因子

容量的默认大小是 16，负载因子是 0.75，当 `HashMap` 的 `size > 16*0.75` 时就会发生扩容(容量和负载因子都可以自由调整)。

### put 方法
首先会将传入的 Key 做 `hash` 运算计算出 hashcode,然后根据数组长度取模计算出在数组中的 index 下标。
由于在计算中位运算比取模运算效率高的多，所以 HashMap 规定数组的长度为 `2^n` 。这样用 `2^n - 1` 做位运算与取模效果一致，并且效率还要高出许多。
由于数组的长度有限，所以难免会出现不同的 Key 通过运算得到的 index 相同，这种情况可以利用链表来解决，HashMap 会在 `table[index]`处形成环形链表，采用头插法将数据插入到链表中。

### get 方法
get 和 put 类似，也是将传入的 Key 计算出 index ，如果该位置上是一个链表就需要遍历整个链表，通过 `key.equals(k)` 来找到对应的元素。

### 遍历方式
```java_holder_method_tree
Iterator<Map.Entry<String, Integer>> entryIterator = map.entrySet().iterator();
while (entryIterator.hasNext()) {
  Map.Entry<String, Integer> next = entryIterator.next();
  System.out.println("key=" + next.getKey() + " value=" + next.getValue());
}
```

```java_holder_method_tree
Iterator<String> iterator = map.keySet().iterator();
      while (iterator.hasNext()){
          String key = iterator.next();
          System.out.println("key=" + key + " value=" + map.get(key));

      }
```

```java_holder_method_tree
map.forEach((key,value)->{
  System.out.println("key=" + key + " value=" + value);
});
```
**强烈建议**使用第一种 EntrySet 进行遍历。

第一种可以把 key value 同时取出，第二种还得需要通过 key 取一次 value，效率较低, 第三种需要 `JDK1.8` 以上，通过外层遍历 table，内层遍历链表或红黑树。 

## notice注意事项
在并发环境下使用 `HashMap` 容易出现死循环。

并发场景发生扩容，调用 `resize()` 方法里的 `rehash()` 时，容易出现环形链表。这样当获取一个不存在的 `key` 时，计算出的 `index` 正好是环形链表的下标时就会出现死循环。

![](https://ws2.sinaimg.cn/large/006tNc79gy1fn85u0a0d9j30n20ii0tp.jpg)

> 所以 HashMap 只能在单线程中使用，并且尽量的预设容量，尽可能的减少扩容。

在 `JDK1.8` 中对 `HashMap` 进行了优化：
当 `hash` 碰撞之后写入链表的长度超过了阈值(默认为8)，链表将会转换为**红黑树**。
假设 `hash` 冲突非常严重，一个数组后面接了很长的链表，此时重新的时间复杂度就是 `O(n)` 。
如果是红黑树，时间复杂度就是 `O(logn)` 。
大大提高了查询效率。

多线程场景下推荐使用 [ConcurrentHashMap](https://github.com/crossoverJie/Java-Interview/blob/master/MD/ConcurrentHashMap.md)。

## LinkedHashMap
> LinkedHashMap使用双向链表来维护key-value对的次序（只需要考虑key次序），该链表负责维护Map的迭代顺序，迭代顺序与key-value对的插入顺序保持一致。
LinkedHashMap可以避免对HashMap、Hashtable里的key-value对进行排序（只要插入key-value对时保持顺序即可），同时又可避免使用TreeMap所增加的成本。
LinkedHashMap需要维护元素的插入顺序，因此性能略低于HashMap的性能； 
原因：它以链表来维护内部顺序，所以在迭代访问Map里的全部元素时将有较好的性能。LinkedHashMap可以记住key-value对的添加顺序。

## TreeMap

> TreeMap两种排序方式：自然排序、定制排序。类似于TreeSet中判断两个元素相等的标准，TreeMap判断两个元素相等的标准是： 
两个key通过compareTo()方法返回0，TreeMap即认为这两个key是相等的。
如果使用自定义类作为TreeMap的key,且想让TreeMap良好的工作，
则重写该类的equals()方法和compareTo()方法时应保持一致的返回结果：两个key通过equals()方法比较返回true时，它们通过compareTo()方法比较应该返回0。如果equals()方法与compareTo()方法返回不一致，TreeMap与Map接口的规则就会冲突。

## fail-fast and fail-safe（快速失败和失败安全）？

The main distinction between fail-fast and fail-safe iterators is whether or not the collection can be modified while it is being iterated. 
Fail-safe iterators allow this; fail-fast iterators do not.
Fail-fast iterators operate directly on the collection itself. 
During iteration, fail-fast iterators fail as soon as they realize that the collection has been modified (i.e., upon realizing that a member has been added, modified, or removed) and will throw a ConcurrentModificationException.
Some examples include ArrayList, HashSet, and HashMap (most JDK1.4 collections are implemented to be fail-fast).

Fail-safe iterates operate on a cloned copy of the collection and therefore do not throw an exception if the collection is modified during iteration. 
Examples would include iterators returned by ConcurrentHashMap or CopyOnWriteArrayList.
