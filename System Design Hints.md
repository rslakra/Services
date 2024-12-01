# System Design Hints

---

## Task Scheduling

### Resource Requirements

The requirements include the number of CPU cores it needs, how much RAM is required to execute this task, how much disk space is required, what should the disk access rate be (input/output rate per second or IOPS), and how many TCP ports the task needs for the execution, and so on.


### Task Category

- Urgent Tasks
- Tasks that can be delayed
- Periodic Tasks

Multiple queues based on the task categories

Delay tolerance is the maximum amount of time a task execution could be delayed.



## Load Balancing

### P2C（Power of Two Choices)

Specifically, the P2C algorithm implements the following：

- Sort all available nodes by load size, from small to size.
- Select two nodes randomly.
- Select the less loaded nodes in two nodes as the nodes selected by the load balancer. 


##


# Author

---

- Rohtash Lakra
