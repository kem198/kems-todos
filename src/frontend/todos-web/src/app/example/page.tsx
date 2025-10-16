import { Button } from "@/components/ui/button";
import Link from "next/link";

export default function ExamplePage() {
  return (
    <div className="flex flex-col gap-4">
      <ul>
        <li>
          <Link href="/example/greeting">
            <Button variant="link">/example/greeting</Button>
          </Link>
        </li>
        <li>
          <Link href="/example/hello">
            <Button variant="link">/example/hello</Button>
          </Link>
        </li>
        <li>
          <Link href="/example/shadcn">
            <Button variant="link">/example/shadcn</Button>
          </Link>
        </li>
      </ul>
    </div>
  );
}
