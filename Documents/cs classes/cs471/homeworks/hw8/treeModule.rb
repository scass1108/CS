#!/usr/bin/env ruby

# sample tree class
class Tree
	attr_reader :label, :left, :right
	def initialize(label, left = nil,  right= nil )
		raise "Label must be provided" if label == nil
		@label = label
		@left = left
		@right = right
	end
	
	def to_s
		s = label.to_s
		if @left != nil then
			s = s.to_s + " " + @left.to_s
		end
		if nil != @right then
			s = s.to_s + " " +@right.to_s
		end
	end
	def each ()
		if  @left != nil  then 
			@left.each{ |y| yield y} 
		end
		yield label
		if  @right != nil  then 
			@right.each{ |y| yield y} 
		end
	end
	
########

	def preOrder()
		yield label
		if @left != nil then
			@left.each{|y| yield y}
		end
	
		if @right != nil then
			@right.each{|y| yield y}
		end
	end

	def postOrder()
		if @left != nil then
			@left.each{|y| yield y}
		end
		
		if @right != nil then 
			@right.each{|y| yield y}
		end
		
		yield label
	end

#######	
	
	
	
	
	
	def Tree.factory(lst)
		n = lst.length
		if n == 0 then
			return nil
		end
		if n == 1 then
			return Tree.new(lst[0])
		end
		i = n /2
		return Tree.new(lst[i], Tree.factory(lst[0 .. i-1]),Tree.factory(lst[i+1 .. n-1]))
	end
	
	def Tree.printIndent(tree, level=0)
		
		if tree.right != nil then
			Tree.printIndent(tree.right, level+1)
		end
		puts '  '*level + level.to_s
		if tree.left != nil then
			Tree.printIndent(tree.left, level+1)
		end
	end
	
	def Tree.prt(tree, level = 0)
		if tree.right != nil then
			Tree.prt(tree.right, level +1)
		end
		puts '  '*level + tree.label.to_s
		if tree.left != nil then
			Tree.prt(tree.left, level+1)
		end
	end
	
    def Tree.loop (tree)
		tree.each{|i| puts i + 9999}
	end
end

# some testing
tree1 = Tree.factory([11,21,31,41,51,61,71,81,91])
Tree.printIndent(tree1)
Tree.loop(tree1)
tree1.each {|e| puts "a "+e.to_s}
Tree.prt(tree1)

tree2 = Tree.factory(["a","b","c"])

puts "inorder: "
tree2.each {|e| puts e.to_s}
puts "Preorder: "
tree2.preOrder {|e| puts e.to_s}
puts"postorder: "
tree2.postOrder {|e| puts e.to_s}
 


