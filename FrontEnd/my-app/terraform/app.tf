resource "aws_key_pair" "deployer" {
  key_name   = "deployer_key"
  public_key = var.public_key

}



resource "aws_launch_configuration" "sept-fri-1030-5" {
  name            = "web_config"
  image_id        = var.ami_id
  instance_type   = "t2.micro"
  security_groups = [aws_security_group.allow_http_ssh.id]

  key_name = aws_key_pair.deployer.key_name
}

resource "aws_lb_target_group" "sept-fri-1030-5" {
  name     = "sept-fri-1030-5-target-group"
  port     = 80
  protocol = "HTTP"
  vpc_id   = aws_vpc.main.id
}

resource "aws_autoscaling_group" "sept-fri-1030-5" {
  name                 = "terraform-asg-example"
  launch_configuration = aws_launch_configuration.sept-fri-1030-5.name
  max_size             = 1
  min_size             = 1
  vpc_zone_identifier  = [aws_subnet.private_az1.id, aws_subnet.private_az2.id, aws_subnet.private_az3.id]
  target_group_arns    = [aws_lb_target_group.sept-fri-1030-5.arn]
}

resource "aws_lb" "sept-fri-1030-5" {
  name               = "sept-fri-1030-5-lb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.allow_http_ssh.id]
  subnets            = [aws_subnet.private_az1.id, aws_subnet.private_az2.id, aws_subnet.private_az3.id]

  tags = {
    Environment = "production"
  }
}

resource "aws_lb_listener" "frint_end" {
  load_balancer_arn = aws_lb.sept-fri-1030-5.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.sept-fri-1030-5.arn
  }
}