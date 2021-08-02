package main

import (
	"log"
	"google.golang.org/grpc"
	"os"
	"time"
	pb "snippet/proto"
	"context"
)

const (
	address     = "39.107.224.111:8959"
	defaultName = "32f3ec1f64f04981a9d92ab37750105f"
)

func main() {
	// Set up a connection to the server.
	log.Println("Set up a connection to the server.")
	//conn, err := grpc.Dial(address, grpc.WithInsecure(), grpc.WithBlock())
	conn, err := grpc.Dial(address, grpc.WithInsecure())
	if err != nil {
		log.Fatalf("did not connect: %v", err)
	}
	log.Println("Conn %v  %v", conn.Target(), conn.GetState())
	defer conn.Close()
	c := pb.NewDelogoSubmiterClient(conn)

	// Contact the server and print out its response.
	log.Println("Contact the server and print out its response.")
	name := defaultName
	if len(os.Args) > 1 {
		name = os.Args[1]
	}
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()
	log.Println("Submit %v", name)
	r, err := c.Submit(ctx, &pb.DelogoSubmitRequest{FileKey: "32f3ec1f64f04981a9d92ab37750105f"})
	if err != nil {
		log.Fatalf("could not greet: %v", err)
	}
	log.Printf("Greeting: %v", r.GetSuccess())
}
