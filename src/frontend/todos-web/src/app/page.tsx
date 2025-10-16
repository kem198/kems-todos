import { Button } from "@/components/ui/button";
import Link from "next/link";

export default function Home() {
  return (
    <div className="p-4 font-sans">
      <main className="row-start-2 flex flex-col items-center gap-[32px] sm:items-start">
        <h1 className="scroll-m-20 text-center text-4xl font-extrabold tracking-tight text-balance">
          todos-web
        </h1>
        <ul>
          <li>
            <Link href="/example">
              <Button variant="link">/example</Button>
            </Link>
          </li>
        </ul>
      </main>
    </div>
  );
}
