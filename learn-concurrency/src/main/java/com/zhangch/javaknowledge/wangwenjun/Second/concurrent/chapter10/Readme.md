![](.Readme_images/0823002d.png)
![](.Readme_images/82e2aeb2.png)
![](.Readme_images/afcb3a13.png)
![](.Readme_images/5b85fd2e.png)
![](.Readme_images/2bb1b4dc.png)
![](.Readme_images/3f6929aa.png)
![](.Readme_images/ad8fd482.png)
![](.Readme_images/e58436a7.png)
![](.Readme_images/b459cc14.png)
![](.Readme_images/152be69c.png)
![](.Readme_images/6c83f914.png)
![](.Readme_images/a3d112ca.png)
ThreadLocal 依然是存在内存泄露隐患的，已知
ThreadMap的Key是ThreadLocal Value是数据，所以当ThreadLocal
为null时Value应该会被回收，但是如果ThreadLocal对应的Thread
依旧存活因为Thread直接关联了ThreadLocalMap会导致Value 不被回收
造成内存泄露