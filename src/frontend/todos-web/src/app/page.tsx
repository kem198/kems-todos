import { Page } from "@/components/layout/page";
import { TypographyH1 } from "@/components/typography/typography-h1";
import { Button } from "@/components/ui/button";
import Link from "next/link";

export default function Home() {
  return (
    <Page>
      <TypographyH1>todos-web</TypographyH1>
      <ul>
        <li>
          <Link href="/example">
            <Button variant="link">/example</Button>
          </Link>
        </li>
        <li>
          <Link href="/todos">
            <Button variant="link">/todos</Button>
          </Link>
        </li>
      </ul>
    </Page>
  );
}
